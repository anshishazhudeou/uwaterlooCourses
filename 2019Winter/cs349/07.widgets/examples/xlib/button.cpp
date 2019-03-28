/*
CS 349 Code Examples: X Windows and XLib

    button.cpp   Implement a ToggleButton UI Widget

- - - - - - - - - - - - - - - - - - - - - -

See associated makefile for compiling instructions

*/

#include <cstdlib>

#include <unistd.h>
#include <sys/time.h>
#include <math.h>

#include <iostream>
#include <sstream>
#include <list>
#include <vector>

#include <X11/Xlib.h>
#include <X11/Xutil.h>



// get microseconds
unsigned long now() {
	timeval tv;
	gettimeofday(&tv, NULL);
	return tv.tv_sec * 1000000 + tv.tv_usec;
}


using namespace std;


// - - - X globals - - -

struct XInfo {
	Display* display;
	Window window;
	int screen;
	GC gc;
};

XInfo xinfo;

// frames per second to run animation loop
int FPS = 60;

// helper function to set X foreground colour
enum Colour {BLACK, WHITE};
void setForeground(Colour c) {
	if (c == BLACK) {
		XSetForeground(xinfo.display, xinfo.gc, BlackPixel(xinfo.display, xinfo.screen));
	} else {
		XSetForeground(xinfo.display, xinfo.gc, WhitePixel(xinfo.display, xinfo.screen));
	}
}


// isPaused functionality
bool isPaused = false;
// isPaused callback (a simple event handler)
void togglePause(bool isOn) {
	isPaused = isOn;
}

// A toggle button widget
class ToggleButton {

public:

	ToggleButton(int _x, int _y, void (*_toggleEvent)(bool)) {
		x = _x;
		y = _y;
		toggleEvent = _toggleEvent;
		isOn = false;
		diameter = 50;
	}

	// the CONTROLLER
	void mouseClick(int mx, int my) {
		float dist = sqrt(pow(mx - x, 2) + pow(my - y, 2));
		if (dist < diameter / 2) {
			toggle();
		}
	}

	// the VIEW
	void draw() {

		if (isOn) {
			setForeground(BLACK);
			XFillArc(xinfo.display, xinfo.window, xinfo.gc,
			         x - diameter / 2,
			         y - diameter / 2,
			         diameter, diameter,
			         0, 360 * 64);
			setForeground(WHITE);
			XDrawArc(xinfo.display, xinfo.window, xinfo.gc,
			         x - diameter / 2,
			         y - diameter / 2,
			         diameter, diameter,
			         0, 360 * 64);
		} else {
			setForeground(WHITE);
			XFillArc(xinfo.display, xinfo.window, xinfo.gc,
			         x - diameter / 2,
			         y - diameter / 2,
			         diameter, diameter,
			         0, 360 * 64);
			setForeground(BLACK);
			XDrawArc(xinfo.display, xinfo.window, xinfo.gc,
			         x - diameter / 2,
			         y - diameter / 2,
			         diameter, diameter,
			         0, 360 * 64);
		}
	}

private:

	// VIEW "essential geometry"
	int x;
	int y;
	int diameter;

	// toggle event callback
	void (*toggleEvent)(bool);

	// the MODEL
	bool isOn;

	void toggle() {
		isOn = !isOn;
		toggleEvent(isOn);
	}
};


int main( int argc, char *argv[] ) {

	xinfo.display = XOpenDisplay("");
	if (xinfo.display == NULL) exit (-1);
	int screennum = DefaultScreen(xinfo.display);
	long background = WhitePixel(xinfo.display, screennum);
	long foreground = BlackPixel(xinfo.display, screennum);
	xinfo.window = XCreateSimpleWindow(xinfo.display, DefaultRootWindow(xinfo.display),
	                                   10, 10, 300, 200, 2, foreground, background);
	XSelectInput(xinfo.display, xinfo.window,
	             ButtonPressMask | KeyPressMask); // select events
	XMapRaised(xinfo.display, xinfo.window);
	XFlush(xinfo.display);

	XEvent event; // save the event here

	// ball postition, size, and velocity
	XPoint ballPos;
	ballPos.x = 50;
	ballPos.y = 50;
	int ballSize = 50;
	XPoint ballDir;
	ballDir.x = 3;
	ballDir.y = 3;

	// create gc for drawing
	xinfo.gc = XCreateGC(xinfo.display, xinfo.window, 0, 0);

	// time of last xinfo.window paint
	unsigned long lastRepaint = 0;

	XWindowAttributes w;
	XGetWindowAttributes(xinfo.display, xinfo.window, &w);

	ToggleButton toggleButton(150, 100, &togglePause);

	// event loop
	while ( true ) {

		// TRY THIS
		// comment out this conditional to see what happens when
		// events block (run the program and keep pressing the mouse)
		if (XPending(xinfo.display) > 0) {
			XNextEvent( xinfo.display, &event );

			switch ( event.type ) {

			// mouse button press
			case ButtonPress:
				// cout << "ButtonPress" << endl;
				toggleButton.mouseClick(event.xbutton.x, event.xbutton.y);
				break;

			case KeyPress: // any keypress
				KeySym key;
				char text[10];
				int i = XLookupString( (XKeyEvent*)&event, text, 10, &key, 0 );
				if ( i == 1 && text[0] == 'q' ) {
					XCloseDisplay(xinfo.display);
					exit(0);
				}
				break;
			}
		}

		unsigned long end = now();

		if (end - lastRepaint > 1000000 / FPS) {


			// clear background
			XClearWindow(xinfo.display, xinfo.window);

			// draw ball from centre
			setForeground(BLACK);
			XFillArc(xinfo.display, xinfo.window, xinfo.gc,
			         ballPos.x - ballSize / 2,
			         ballPos.y - ballSize / 2,
			         ballSize, ballSize,
			         0, 360 * 64);


			if (!isPaused) {

				// update ball position
				ballPos.x += ballDir.x;
				ballPos.y += ballDir.y;

				// bounce ball
				if (ballPos.x + ballSize / 2 > w.width ||
				        ballPos.x - ballSize / 2 < 0)
					ballDir.x = -ballDir.x;
				if (ballPos.y + ballSize / 2 > w.height ||
				        ballPos.y - ballSize / 2 < 0)
					ballDir.y = -ballDir.y;

			}

			toggleButton.draw();

			XFlush( xinfo.display );

			lastRepaint = now(); // remember when the paint happened
		}

		// IMPORTANT: sleep for a bit to let other processes work
		if (XPending(xinfo.display) == 0) {
			usleep(1000000 / FPS - (end - lastRepaint));
		}


	}
	XCloseDisplay(xinfo.display);
}

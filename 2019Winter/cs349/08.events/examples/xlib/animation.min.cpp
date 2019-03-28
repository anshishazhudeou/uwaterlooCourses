/*
CS 349 Code Examples: X Windows and XLib

    animation.cpp   Ball bouncing in window (minimal version)

- - - - - - - - - - - - - - - - - - - - - -

See associated makefile for compiling instructions

*/

#include <cstdlib>
#include <iostream>
#include <unistd.h>
#include <sys/time.h>

#include <X11/Xlib.h>
#include <X11/Xutil.h>



// get microseconds
unsigned long now() {
	timeval tv;
	gettimeofday(&tv, NULL);
	return tv.tv_sec * 1000000 + tv.tv_usec;
}


using namespace std;

Display* display;
Window window;

// frames per second to run animation loop
int FPS = 60;

int main( int argc, char *argv[] ) {
	display = XOpenDisplay("");
	if (display == NULL) exit (-1);
	int screennum = DefaultScreen(display);
	long background = WhitePixel(display, screennum);
	long foreground = BlackPixel(display, screennum);
	window = XCreateSimpleWindow(display, DefaultRootWindow(display),
	                             10, 10, 300, 200, 2, foreground, background);
	XSelectInput(display, window,
	             ButtonPressMask | KeyPressMask); // select events
	XMapRaised(display, window);
	XFlush(display);

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
	GC gc = XCreateGC(display, window, 0, 0);

	// time of last window paint
	unsigned long lastRepaint = 0;

	XWindowAttributes w;
	XGetWindowAttributes(display, window, &w);

	// event loop
	while ( true ) {

		// TRY THIS
		// comment out this conditional to see what happens when
		// events block (run the program and keep pressing the mouse)
		if (XPending(display) > 0) { 
			XNextEvent( display, &event ); 

			switch ( event.type ) {

			// mouse button press
        	case ButtonPress:
	            cout << "CLICK" << endl;
            	break;

			case KeyPress: // any keypress
			    KeySym key;
				char text[10];
          		int i = XLookupString( (XKeyEvent*)&event, text, 10, &key, 0 );
            	if ( i == 1 && text[0] == 'q' ) {
                	XCloseDisplay(display);
                	exit(0);
            	}
				break;
			}
		}

		unsigned long end = now();	// get time in microsecond

		if (end - lastRepaint > 1000000 / FPS) { 

			// clear background
			XClearWindow(display, window);

			// draw ball from centre
			XFillArc(display, window, gc, 
				ballPos.x - ballSize/2, 
				ballPos.y - ballSize/2, 
				ballSize, ballSize,
				0, 360*64);

			// update ball position
			ballPos.x += ballDir.x;
			ballPos.y += ballDir.y;

			// bounce ball
			if (ballPos.x + ballSize/2 > w.width ||
				ballPos.x - ballSize/2 < 0)
				ballDir.x = -ballDir.x;
			if (ballPos.y + ballSize/2 > w.height ||
				ballPos.y - ballSize/2 < 0)
				ballDir.y = -ballDir.y;

			XFlush( display );

			lastRepaint = now(); // remember when the paint happened
		}

		// IMPORTANT: sleep for a bit to let other processes work
		if (XPending(display) == 0) {
			usleep(1000000 / FPS - (now() - lastRepaint));
		}
	}
	XCloseDisplay(display);
}

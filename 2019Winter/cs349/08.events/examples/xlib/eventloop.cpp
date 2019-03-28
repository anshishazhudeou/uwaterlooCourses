/*
CS 349 Code Examples: X Windows and XLib

    eventloop     Displays text at cursor location on mouse button press

- - - - - - - - - - - - - - - - - - - - - -

See associated makefile for compiling instructions

*/

#include <iostream>
#include <list>
#include <cstdlib>
#include <vector>

//#include <string>
//#include <sstream>

#include <X11/Xlib.h>
#include <X11/Xutil.h>

using namespace std;


const int Border = 5;
const int BufferSize = 10;

int count_click = 0;

struct XInfo {
    Display*  display;
    Window   window;
    GC       gc;
};


// An abstract class representing displayable things.
class Displayable {
public:
    virtual void paint(XInfo& xinfo) = 0;
};

// A text displayable
class Text : public Displayable {
public:
    virtual void paint(XInfo& xinfo) {
        XDrawImageString( xinfo.display, xinfo.window, xinfo.gc,
                          this->x, this->y, this->s.c_str(), this->s.length() );
    }

    Text(int x, int y, string s): x(x), y(y), s(s)  {}

private:
    int x;
    int y;
    string s; // string to show
};

// A displayable polyline
class Polyline : public Displayable {
public:
    virtual void paint(XInfo& xinfo) {
        // note the trick to pass a stl vector as an C array
        XDrawLines(xinfo.display, xinfo.window, xinfo.gc,
                   &points[0], points.size(),  // vector of points
                   CoordModeOrigin ); // use absolute coordinates
    }

    Polyline(int x, int y) {
        add_point(x, y);
    }

    // add another point to the line
    void add_point(int x, int y) {
        XPoint p; 
        p.x = x;
        p.y = y;
        points.push_back(p);
    }

private:
    // need to use a vector to dynamically add points
    vector < XPoint > points; // XPoint is a built in struct
};

// Function to put out a message on error exits.
void error( string str ) {
    cerr << str << endl;
    exit(0);
}

// Function to repaint a display list
void repaint( list<Displayable*> dList, XInfo& xinfo) {
    list<Displayable*>::const_iterator begin = dList.begin();
    list<Displayable*>::const_iterator end = dList.end();

    XClearWindow( xinfo.display, xinfo.window );
    while ( begin != end ) {
        Displayable* d = *begin;
        d->paint(xinfo);
        begin++;
    }
    XFlush( xinfo.display );
}



// The loop responding to events from the user.
void eventloop(XInfo& xinfo) {
    XEvent event;
    KeySym key;
    char text[BufferSize];
    list<Displayable*> dList;        

    Polyline* polyline = NULL;

    while ( true ) {
        XNextEvent( xinfo.display, &event );

        switch ( event.type ) {

        // add item where mouse clicked
        case ButtonPress:
            // output to console
            cout << "ButtonPress at " << event.xbutton.time << endl;

            // add a new text object to the display list
            dList.push_back(new Text(
                event.xbutton.x, event.xbutton.y, "CLICK"));                
            // start a new polyline to draw
            polyline = new Polyline(event.xbutton.x, event.xbutton.y);
            dList.push_back(polyline);
            repaint( dList, xinfo );
            break;

        // add item where mouse moved
        case MotionNotify:

            // add the point to the drawing
            if (polyline != NULL) {
                polyline->add_point(event.xmotion.x, 
                    event.xmotion.y);

            }
            repaint( dList, xinfo );

            break;
        /*
         * Exit when 'q' is typed.
         * Arguments for XLookupString :
         *                 event - the keyboard event
         *                 text - buffer into which text will be written
         *                 BufferSize - size of text buffer
         *                 key - workstation independent key symbol
         *                 0 - pointer to a composeStatus structure
         */
        case KeyPress:
            int i = XLookupString( 
                (XKeyEvent*)&event, text, BufferSize, &key, 0 );
            cout << "KeySym " << key
                 << " text='" << text << "'"
                 << " at " << event.xkey.time

                 << endl;
            if ( i == 1 && text[0] == 'q' ) {
                cout << "Terminated normally." << endl;
                XCloseDisplay(xinfo.display);
                return;
            }

            switch(key){
                case XK_Up:
                    cout << "Up" << endl;
                    break;
                case XK_Down:
                    cout << "Down" << endl;
                    break;
                case XK_Left:
                    cout << "Left" << endl;
                    break;
                case XK_Right:
                    cout << "Right" << endl;
                    break;
                }

            break;
        }
    }
}

//  Create the window;  initialize X.
void initX(int argc, char* argv[], XInfo& xinfo) {

    xinfo.display = XOpenDisplay( "" );
    if ( !xinfo.display ) {
        error( "Can't open display." );
    }

    int screen = DefaultScreen( xinfo.display );
    unsigned long background = WhitePixel( xinfo.display, screen );
    unsigned long foreground = BlackPixel( xinfo.display, screen );

 
    XSizeHints hints;
    hints.x = 100;
    hints.y = 100;
    hints.width = 400;
    hints.height = 300;
    hints.flags = PPosition | PSize;
    xinfo.window = XCreateSimpleWindow( xinfo.display, DefaultRootWindow( xinfo.display ),
                                        hints.x, hints.y, hints.width, hints.height,
                                        Border, foreground, background );
    XSetStandardProperties( xinfo.display, xinfo.window, "eventloop", "eventloop", None,
                            argv, argc, &hints );


    xinfo.gc = XCreateGC (xinfo.display, xinfo.window, 0, 0 );
    XSetBackground( xinfo.display, xinfo.gc, background );
    XSetForeground( xinfo.display, xinfo.gc, foreground );

    // Tell the base window system what input events you want.
    // ButtomMotionMask: The client application receives MotionNotify events only when at least one button is pressed.
    XSelectInput( xinfo.display, xinfo.window,
                  ButtonPressMask | KeyPressMask | ButtonMotionMask );

    XMapRaised( xinfo.display, xinfo.window );
}



int main ( int argc, char* argv[] ) {
    XInfo xinfo;
    initX(argc, argv, xinfo);
    eventloop(xinfo);
}

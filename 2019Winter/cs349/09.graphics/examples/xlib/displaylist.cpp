/*
CS 349 Code Examples: X Windows and XLib

    displaylist     Demos Displayable class and display list technique

- - - - - - - - - - - - - - - - - - - - - -

See associated makefile for compiling instructions

*/

#include <iostream>
#include <list>
#include <cstdlib>
#include <vector>
/*
 * Header files for X functions
 */
#include <X11/Xlib.h>
#include <X11/Xutil.h>

#include <unistd.h> // needed for sleep

using namespace std;

/*
 * Information to draw on the window.
 */
struct XInfo {
  Display*  display;
  Window   window;
  GC       gc;
};


/*
 * An abstract class representing displayable things.
 */
class Displayable {
public:
  virtual void paint(XInfo& xinfo) = 0;
};

/*
 * A text displayable
 */
class Text : public Displayable {
public:
  virtual void paint(XInfo& xinfo) {
    XDrawImageString( xinfo.display, xinfo.window, xinfo.gc,
                      this->x, this->y, this->s.c_str(), this->s.length() );
  }

  // constructor
  Text(int x, int y, string s): x(x), y(y), s(s)  {}

private:
  int x;
  int y;
  string s; // string to show
};

/*
 * A displayable polyline
 */
class Polyline : public Displayable {
public:
  virtual void paint(XInfo& xinfo) {
    // note the trick to pass a stl vector as an C array
    XDrawLines(xinfo.display, xinfo.window, xinfo.gc,
               &points[0], points.size(),  // vector of points
               CoordModeOrigin ); // use absolute coordinates
  }

  // constructor
  Polyline(int x, int y) {
    add_point(x, y);
  }

  // add another point to the line
  void add_point(int x, int y) {
    XPoint p; // XPoint is a built in struct
    p.x = x;
    p.y = y;
    points.push_back(p);
  }

private:
  // need to use a vector to dynamically add points
  vector < XPoint > points; // XPoint is a built in struct
};



/*
 * A face displayable
 */
class Face : public Displayable {
public:
  virtual void paint(XInfo& xinfo) {

     // create a simple graphics context
    GC gc = XCreateGC(xinfo.display, xinfo.window, 0, 0);
    int screen = DefaultScreen( xinfo.display );
    XSetForeground(xinfo.display, gc, BlackPixel(xinfo.display, screen));
    XSetBackground(xinfo.display, gc, WhitePixel(xinfo.display, screen));
    XSetFillStyle(xinfo.display,  gc, FillSolid);
    XSetLineAttributes(xinfo.display, gc,
                       3,       // 3 is line width
                       LineSolid, CapButt, JoinRound);         // other line options


    // draw head
    XFillArc(xinfo.display, xinfo.window, gc,
             x - (d / 2), y - (d / 2), d, d, 0, 360 * 64);

    XSetForeground(xinfo.display, gc, WhitePixel(xinfo.display, screen));

    // draw mouth either smiling or serious
    if (smile) {
      int dd = d / 2;
      XDrawArc(xinfo.display, xinfo.window, gc,
               x - (dd / 2), y - (dd / 2), dd, dd, 210 * 64, 120 * 64);
    } else {
      int dd = d / 3;
      XDrawLine(xinfo.display, xinfo.window, gc,
                x - dd, y + dd / 2, x + dd, y + dd / 2);
    }
  }

  // constructor
  Face(int x, int y, int d, bool smile): x(x), y(y), d(d), smile(smile) {}

private:
  int x;
  int y;
  int d; // diameter
  bool smile; // is smiling
};



/*
 * Function to put out a message on error exits.
 */
void error( string str ) {
  cerr << str << endl;
  exit(0);
}


/*
 * Create a window
 */
void initX(int argc, char* argv[], XInfo& xinfo) {


  /*
  * Display opening uses the DISPLAY  environment variable.
  * It can go wrong if DISPLAY isn't set, or you don't have permission.
  */
  xinfo.display = XOpenDisplay( "" );
  if ( !xinfo.display ) {
    error( "Can't open display." );
  }

  /*
  * Find out some things about the display you're using.
  */
  // DefaultScreen is as macro to get default screen index
  int screen = DefaultScreen( xinfo.display );

  unsigned long white, black;
  white = XWhitePixel( xinfo.display, screen );
  black = XBlackPixel( xinfo.display, screen );

  xinfo.window = XCreateSimpleWindow(
                   xinfo.display,       // display where window appears
                   DefaultRootWindow( xinfo.display ), // window's parent in window tree
                   10, 10,                  // upper left corner location
                   300, 300,                  // size of the window
                   5,               // width of window's border
                   black,           // window border colour
                   white );             // window background colour

  // extra window properties like a window title
  XSetStandardProperties(
    xinfo.display,    // display containing the window
    xinfo.window,   // window whose properties are set
    "displaylist",  // window's title
    "DL",       // icon's title
    None,       // pixmap for the icon
    argv, argc,     // applications command line args
    None );         // size hints for the window

  /*
   * Put the window on the screen.
   */
  XMapRaised( xinfo.display, xinfo.window );

  XFlush(xinfo.display);

  // give server time to setup
  sleep(1);
}

/*
 * Function to repaint a display list
 */
void repaint( list<Displayable*> dList, XInfo& xinfo) {
  list<Displayable*>::const_iterator begin = dList.begin();
  list<Displayable*>::const_iterator end = dList.end();

  XClearWindow(xinfo.display, xinfo.window);
  while ( begin != end ) {
    Displayable* d = *begin;
    d->paint(xinfo);
    begin++;
  }
  XFlush(xinfo.display);
}


int main( int argc, char* argv[] ) {

  XInfo xinfo;

  initX(argc, argv, xinfo);

  // create a simple graphics context
  GC gc = XCreateGC(xinfo.display, xinfo.window, 0, 0);
  int screen = DefaultScreen( xinfo.display );
  XSetForeground(xinfo.display, gc, BlackPixel(xinfo.display, screen));
  XSetBackground(xinfo.display, gc, WhitePixel(xinfo.display, screen));

  // load a larger font
  XFontStruct * font;
  font = XLoadQueryFont (xinfo.display, "12x24");
  XSetFont (xinfo.display, gc, font->fid);

  xinfo.gc = gc;

   // list of Displayables
  list<Displayable*> dList;

  Polyline* polyline = new Polyline(0, 0);
  for (int i = 0; i < 100; i++) {
    polyline->add_point(rand() % 300, rand() % 300);
  }

  // try changing the order or commenting out these lines


  dList.push_back(new Text(50, 50, "ABC"));

  dList.push_back(new Text(130, 100, "DEF"));

  dList.push_back(new Text(200, 200, "GHI"));

  dList.push_back(new Face(200, 200, 50, false));

  dList.push_back(polyline);


  // paint everything in the display list
  repaint(dList, xinfo);

  XFlush(xinfo.display);

  std::cout << "ENTER2exit"; std::cin.get(); // wait for input
  XCloseDisplay(xinfo.display);

}
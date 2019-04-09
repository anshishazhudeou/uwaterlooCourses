# A4: Mobile Layout (Java/Android) -- Fotag
### Compiling
  - Navigate to a4/ at the same level of app folder
  - Run gradle build, it will automatically build the project
  - Then run this Fotag with virtual device with Pixel using Android Studio
  
### Operation
   - A load button, which loads a set of 10 images over the network from this URL: https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images. If the list already contains images, clicking on this button should clear the list and replace with a fresh set of images from that URL. Note that the images will be replaced for marking, so you shouldn't attempt to embed the images in your app - make sure to load them dynamically over the network.
   - A clear button, which removes all images from the list.
   - A filter widget, showing 5-stars, used to filter the image list based on rating. You should support filtering by "any" image, or "1-5" where the filter shows all images that have that rating or higher.
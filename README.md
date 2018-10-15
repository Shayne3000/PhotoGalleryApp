# A Basic Photo Gallery App

#### Features

1. The app works in both portrait and landscape orientations.
2. The photo grid supports pagination i.e. you can scroll on grid of photos infinitely.
3. When a user taps on a photo in the grid, it shows more information about the photo in a much bigger size.
4. The user can swipe through the photos on the details screen.
5. The photo positions in grid are stored such that the last viewed photo in the details screen is visible in the grid.

#### Warning: There may be an issue with Gradle finding your Manifest file. This popped up with Android Studio 3.2. 
To fix this, set 
javaCompileOptions { annotationProcessorOptions { arguments = ["androidManifestFile":"C:\\source_to_your_project_location\\Project_name\\app\\src\\main\\AndroidManifest.xml"] } } within the defaultConfig block of your module-level gradle file.

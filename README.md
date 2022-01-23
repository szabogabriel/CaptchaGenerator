# CaptchaGenerator

A simple project for generating captcha images in Java.

The main class is the `Captcha` class, which accepts a `String` object and offers methods to access the generated image and also to check the input from the user. There are default image generator and equals function implementations used, for which custom implementation can be provided.

# Usage

## Simple use case

The simplest use case is to create a Captcha instance with a generated string. This data is not stored, only its hash value and the image generated. The default type of the generated image is JPEG. This can be acquired either as a `byte[]`, as a `Base64` encoded string or written to a provided `OutputStream`.

```Java
Captcha captcha = new Captcha("abcdefg"); // create captcha using default values
String captchaImage = captcha.getBase64Image(); // we acquire the JPG image created by the Captcha implementation in embeddable Base64 encoding.
...
String enteredStringFromUser = "abcdefg";
if (captcha.isMatchingString(enteredStringFromUser) { // we cross check the input value with the stored hash
  //TODO accepted input.
}
```
## Custom providers and printers

Custom implementation of image printers and hash calculators can be provided to the Captcha image. The `boolean` attribute in the `DefaultImagePrinter` sets whether stripes should be printed to the image or not.

```Java
Captcha captcha = new Captcha("abcdefg", "png", new DefaultImagePrinter(true), new DefaultHashCalculator()); // Create PNG image with default providers
```

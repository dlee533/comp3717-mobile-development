class Animal {
  int _privateVar; // able to access animal within the same file
  String species;
  String sound;

  Animal({this.species, this.sound});

  // Animal (String species, String sound) {
  //   this.species = species;
  //   this.sound = sound;
  // }
}

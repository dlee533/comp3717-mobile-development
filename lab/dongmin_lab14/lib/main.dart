import 'package:dongmin_lab14/popCulture.dart';
import 'package:dongmin_lab14/popCulture_card.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MaterialApp(
      home: MyApp(),
  ));
}

class MyApp extends StatelessWidget {

  List<PopCulture> popCultures = [
    PopCulture(name: "Lord of the Rings", characters: ["Gandalf", "Frodo Baggins", "Samwise Gamgee", "Sauron", "Gollum"], cardColour: Colors.orange, charColour: Colors.orange[100]),
    PopCulture(name: "Harry Potter", characters: ["Harry", "Ron", "Hermione", "Dumbledore", "Voldemort"], cardColour: Colors.pink, charColour: Colors.pink[100]),
    PopCulture(name: "Avengers", characters: ["Iron Man", "Thor", "Hulk", "Spiderman", "Black Widow"], cardColour: Colors.green, charColour: Colors.green[100]),
    PopCulture(name: "Justice League", characters: ["Superman", "Wonder Woman", "Batman", "Aquaman"], cardColour: Colors.brown, charColour: Colors.brown[100]),
  ];

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Lab 14: Pop Culture Characters"),
      ),
      body: ListView(
        children: popCultures.map((popCulture) {
          return
            PopCultureCard(
            popCulture: popCulture,
          );
        }).toList(),
      ),
    );
  }
}

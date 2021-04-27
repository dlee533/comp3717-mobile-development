import 'package:flutter/material.dart';
import 'animal.dart';

void main() {
  runApp(MaterialApp(
    home: MyApp()
  ));
}

class MyApp extends StatelessWidget {

  List<Animal> animals = [
    Animal(species: "dog", sound: "bark bark"),
    Animal(species: "cat", sound: "meow"),
    Animal(species: "sheep", sound: "bahh"),
    Animal(species: "snake", sound: "ssss"),
    Animal(species: "fish", sound: "blurp"),
  ];

  Widget CardTemplate(Animal animal) {
    return Padding(
      padding: const EdgeInsets.all(18.0),
      child: Card(
        elevation: 25,
        color: Colors.orange,
        child:Padding(
          padding: const EdgeInsets.all(30.0),
          child: Column(
            children: [
              Text('${animal.species} goes ${animal.sound}'),
              TextButton(
                child: Text("Delete"),
                onPressed: (){},
              ),
            ],
          ),
        )
      ),
    );
  }

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          title: Text("Lec14"),
      ),
      body: ListView(
        children: animals.map((animal) {
          return CardTemplate(animal);
        }).toList(),
      )
    );
  }
}
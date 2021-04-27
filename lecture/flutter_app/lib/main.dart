import 'package:flutter/material.dart';

void main() {
  runApp(
    MaterialApp(
      home: Home(),
    ),
  );
}

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {

  int x = 0;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Lecture 13"),
        centerTitle: true,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Container(
              color: Colors.yellow[600],
              child: Text("Click the button!!"),
            ),
            ElevatedButton(
              child: Text("ME!"),
              onPressed: () {},
            )
          ],
        ),
      )
    );
  }
}

// Text(
//   'Good Morning',
//   style: TextStyle(
//     fontSize: 20,
//     fontWeight: FontWeight.bold,
//     color: Colors.deepOrangeAccent,
//     letterSpacing: 2,
//     fontFamily: 'Trainone',
//   ),
// ),

// TextButton(
// child: Text("Click me"),
// onPressed: (){
// print('I clicked the button');
// },
// ),

// ElevatedButton(
// child: Text('Click'),
// onPressed: () {},
// ),

// ElevatedButton.icon(
// label: Text('airplane mode'),
// icon: Icon(Icons.airplanemode_on),
// onPressed: (){},
// ),

// IconButton(
// onPressed: () {},
// icon: Icon(Icons.watch_sharp),
// )

// Container(
// child: Text("Hello World"),
// color: Colors.lightGreen[600],
// padding: EdgeInsets.symmetric(horizontal: 10),
// margin: EdgeInsets.all(30),
// ),

// Row(
// mainAxisAlignment: MainAxisAlignment.spaceEvenly,
// crossAxisAlignment: CrossAxisAlignment.center,
// children: [
// Container(
// child: Text("Hello World"),
// color: Colors.lightGreen[600],
// padding: EdgeInsets.all(20),
// ),
// Container(
// color: Colors.yellow,
// child: Text("Bye"),
// padding: EdgeInsets.all(40),
// ),
// Container(
// color: Colors.pinkAccent,
// child: Text("World"),
// padding: EdgeInsets.all(40),
// )
// ],
// ),
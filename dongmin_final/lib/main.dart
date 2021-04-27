import 'package:dongmin_final/hippo_container.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'hippo.dart';

void main() {
  runApp(MaterialApp(home: Home(), routes: {
    "home": (context) {
      return Home();
    },
    "last": (context) {
      return Last();
    }
  }));
}

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  List<Hippo> hippoList = [
    Hippo(
        order: "first",
        imagePath: "assets/hippo1.png",
        row: Row(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            Text("really enjoys", style: TextStyle(fontWeight: FontWeight.bold)),
            Card(child: Text("oranges"), color: Colors.orange),
            Text("and", style: TextStyle(fontWeight: FontWeight.bold)),
            Card(child: Text("grapefruit"), color: Colors.pink),
          ],
        ),
    ),
    Hippo(
        order: "second",
        imagePath: "assets/hippo2.png",
        row: Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
            Text("really enjoys", style: TextStyle(fontWeight: FontWeight.bold)),
            Card(child: Text("grapes"), color: Colors.purple),
            Text("and", style: TextStyle(fontWeight: FontWeight.bold)),
            Card(child: Text("bananas"), color: Colors.yellow),
        ],
      ),
    ),
    Hippo(
      order: "third",
      imagePath: "assets/hippo3.png",
      row: Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          Text("really enjoys", style: TextStyle(fontWeight: FontWeight.bold)),
          Card(child: Text("apples"), color: Colors.red),
          Text("and", style: TextStyle(fontWeight: FontWeight.bold)),
          Card(child: Text("limes"), color: Colors.green),
        ],
      ),
    ),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          automaticallyImplyLeading: false,
          title: Text("Hungry Hippos"),
        ),
        body: ListView.builder(
          itemCount: hippoList.length + 1,
          itemBuilder: (context, index) {
            if (index != hippoList.length) {
              return HippoContainer(hippo: hippoList[index]);
            }
            return Align(
              alignment: Alignment.bottomCenter,
              child: ElevatedButton(
                child: Text("Next Page"),
                onPressed: () {
                  setState(() {
                    Navigator.pushNamed(context, 'last');
                  });
                },
              ),
            );
          },
        )
    );
  }
}

class Last extends StatefulWidget {
  @override
  _LastState createState() => _LastState();
}

class _LastState extends State<Last> {
  String buttonStr1 = "The end";
  String buttonStr2 = "Fin";
  String buttonStr = "The end";
  Hippo lastHippo = Hippo(
      order: "fourth",
      imagePath: "assets/hippo4.png",
      row: Row(mainAxisAlignment: MainAxisAlignment.spaceEvenly, children: [
        Text("really enjoys..."),
        Text("hey wait a sec!",
            style: TextStyle(fontWeight: FontWeight.bold, fontSize: 22))
      ]));

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          leading: IconButton(
            icon: Icon(Icons.arrow_back),
            onPressed: () {
              Navigator.pushNamed(context, 'home');
            },
          ),
          title: Text("Hungry Hippos")),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        children: [
          Expanded(
            child: ListView(children: [
              HippoContainer(hippo: lastHippo),
            ]),
          ),
          ElevatedButton(
              onPressed: () {
                if (buttonStr == buttonStr1) {
                  setState(() {
                    buttonStr = buttonStr2;
                  });
                } else {
                  setState(() {
                    buttonStr = buttonStr1;
                  });
                }
              },
              child: Text(buttonStr)),
        ],
      ),
    );
  }
}

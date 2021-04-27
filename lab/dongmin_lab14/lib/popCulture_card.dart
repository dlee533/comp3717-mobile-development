import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'popCulture.dart';

class PopCultureCard extends StatelessWidget {

  PopCulture popCulture;

  PopCultureCard({this.popCulture});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Column(
        children: [
          Text(
            popCulture.name,
            style: TextStyle(
              fontWeight: FontWeight.bold,
              fontSize: 18.0,
            )
          ),
          Card(
            color: popCulture.cardColour,
            child: Container(
              height: 130.0,
              child: ListView.builder(
                scrollDirection: Axis.horizontal,
                itemCount: popCulture.characters.length,
                itemBuilder: (context, i){
                  return Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Center(
                      child: Card(
                        color: popCulture.charColour,
                        elevation: 5,
                        child: Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: Text(
                            popCulture.characters[i],
                            style: TextStyle(
                              color: popCulture.cardColour,
                              fontWeight: FontWeight.bold
                            ),
                          ),
                        ),
                      ),
                    ),
                  );
                },
              ),
            ),
          )
        ],
      ),
    );
  }

}
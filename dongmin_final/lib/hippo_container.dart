import 'package:flutter/material.dart';

import 'hippo.dart';

Widget HippoTemplate(Hippo hippo) {
  return HippoContainer();
}

class HippoContainer extends StatelessWidget {
  Hippo hippo;

  HippoContainer({this.hippo});

  @override
  Widget build(BuildContext context) {
    return Container(
        margin: EdgeInsets.all(10),
        child: Column(
          children: [
            Text("The ${hippo.order} hungry hippo", style: TextStyle(fontWeight: FontWeight.bold, fontSize: 15)),
            Image(image: AssetImage(hippo.imagePath),),
            hippo.row
          ],
        )
    );
  }
}
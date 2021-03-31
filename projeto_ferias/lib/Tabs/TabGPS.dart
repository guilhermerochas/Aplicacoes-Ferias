import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';

class TabGPS extends StatefulWidget {
  @override
  _TabGPSState createState() => _TabGPSState();
}

String latitude = '';
String longitude = '';

class _TabGPSState extends State<TabGPS> {
  Position _position;
  Geolocator _geolocator;

  setCurrentPosition() async {
    setState(() {
      _geolocator = Geolocator();
      _geolocator
          .getCurrentPosition(desiredAccuracy: LocationAccuracy.best)
          .then((Position position) {
        setState(() {
          _position = position;
          latitude = position.latitude.toString();
          longitude = position.longitude.toString();
        });
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(left: 20, right: 20),
      child: Column(
        children: <Widget>[
          SizedBox(height: 40),
          Align(
            alignment: Alignment.centerLeft,
            child: Text(
              'Lat: ' + latitude,
              style: TextStyle(fontSize: 25),
            ),
          ),
          SizedBox(height: 15),
          Align(
            alignment: Alignment.centerLeft,
            child: Text(
              'Long: ' + longitude,
              style: TextStyle(fontSize: 25),
            ),
          ),
          SizedBox(height: 20),
          ButtonTheme(
            minWidth: double.infinity,
            child: RaisedButton(
              onPressed: () {
                if (latitude == '') {
                  setState(() {
                    {
                      setCurrentPosition();
                    }
                  });
                }
              },
              child: Text(
                "EXIBIR",
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 15,
                ),
              ),
              color: Theme.of(context).primaryColor,
            ),
          ),
        ],
      ),
    );
  }
}

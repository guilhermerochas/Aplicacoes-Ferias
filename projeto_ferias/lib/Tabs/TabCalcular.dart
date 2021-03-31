import 'package:flutter/material.dart';

class TabCalcular extends StatefulWidget {
  @override
  _TabCalcularState createState() => _TabCalcularState();
}

class _TabCalcularState extends State<TabCalcular> {
  TextEditingController _alturaController = TextEditingController();
  TextEditingController _baseController = TextEditingController();

  double _resultadoArea;

  @override
  void initState() {
    super.initState();
    _resultadoArea = null;
  }

  @override
  void dispose() {
    _alturaController.dispose();
    _baseController.dispose();
    super.dispose();
  }

  String checkAreaResult() {
    if (_resultadoArea == null) {
      return '';
    }
    return _resultadoArea.toString();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(left: 20, right: 20),
      child: Column(
        children: <Widget>[
          SizedBox(height: 40),
          TextField(
            keyboardType: TextInputType.number,
            controller: _alturaController,
            decoration: InputDecoration(
              hintText: "Altura do Triangulo",
              border: UnderlineInputBorder(
                borderSide: BorderSide(
                  color: Theme.of(context).primaryColor,
                  style: BorderStyle.solid,
                  width: 13.0,
                ),
              ),
            ),
          ),
          SizedBox(height: 20),
          TextField(
            keyboardType: TextInputType.number,
            controller: _baseController,
            decoration: InputDecoration(
              hintText: "Base do Triangulo",
              border: UnderlineInputBorder(
                borderSide: BorderSide(
                  color: Theme.of(context).primaryColor,
                  style: BorderStyle.solid,
                  width: 13.0,
                ),
              ),
            ),
          ),
          SizedBox(height: 30),
          Text(
            "A area do triangulo é: " + checkAreaResult(),
            style: TextStyle(fontSize: 25),
          ),
          SizedBox(height: 20),
          ButtonTheme(
            minWidth: double.infinity,
            child: RaisedButton(
              onPressed: () {
                if (_alturaController.text == '' ||
                    _baseController.text == '') {
                  showDialog(
                    context: this.context,
                    builder: (_) => AlertDialog(
                      title: Text("Campos Requeridos"),
                      actions: <Widget>[
                        RaisedButton(
                          color: Theme.of(context).primaryColor,
                          onPressed: () {
                            Navigator.pop(context);
                          },
                          child: Text(
                            "OK",
                            style: TextStyle(color: Colors.white),
                          ),
                        ),
                      ],
                    ),
                  );
                } else {
                  setState(() {
                    _resultadoArea = (double.parse(_alturaController.text) *
                            double.parse(_baseController.text)) /
                        2;
                  });
                }
              },
              child: Text(
                "CALCULAR",
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

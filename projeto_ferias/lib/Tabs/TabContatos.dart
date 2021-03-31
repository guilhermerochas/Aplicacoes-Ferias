import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'package:contacts_service/contacts_service.dart';
import 'package:permission_handler/permission_handler.dart';

class TabContatos extends StatefulWidget {
  @override
  _TabContatosState createState() => _TabContatosState();
}

class _TabContatosState extends State<TabContatos> {
  TextEditingController _contatosController;
  Contact contato;
  bool contatoExiste;

  @override
  void initState() {
    super.initState();
    _contatosController = new TextEditingController();
    contatoExiste = false;
  }

  Future<void> getContact() async {
    List<Contact> _contato = (await ContactsService.getContacts(
      query: _contatosController.text,
    ))
        .toList();
    setState(() {
      if (_contatosController.text.isEmpty || _contato.isEmpty) {
        contatoExiste = false;
      } else {
        contatoExiste = true;
        contato = _contato[0];
      }
    });
  }

  @override
  void dispose() {
    _contatosController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(left: 30, right: 30),
      child: Column(
        children: <Widget>[
          SizedBox(height: 20),
          TextField(
            controller: _contatosController,
            keyboardType: TextInputType.text,
            decoration: InputDecoration(
              hintText: "Digite o nome do contato",
              border: UnderlineInputBorder(
                borderSide: BorderSide(
                  color: Theme.of(context).primaryColor,
                  width: 13.0,
                  style: BorderStyle.solid,
                ),
              ),
            ),
          ),
          SizedBox(height: 50),
          Center(
            child: RaisedButton(
              onPressed: () => {
                Permission.contacts.request().isGranted.then(
                      (_) => getContact().then(
                        (_) => showDialog(
                          context: context,
                          builder: (_) => AlertDialog(
                            title: contatoExiste
                                ? Text(contato.displayName.toString())
                                : Text("not found"),
                            content: contatoExiste
                                ? Text(contato.phones.elementAt(0).value)
                                : null,
                          ),
                        ),
                      ),
                    ),
              },
              child: Text(
                "BUSCAR CONTATO",
                style: TextStyle(
                  color: Colors.white,
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

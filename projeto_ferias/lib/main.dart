import 'package:flutter/material.dart';
import 'Tabs/TabContatos.dart';
import 'Tabs/TabCalcular.dart';
import 'Tabs/TabGPS.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  Color primary = new Color(0xff128C7E);
  Color accent = new Color(0xff075E54);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Projeto Ferias Flutter",
      debugShowCheckedModeBanner: false,
      theme: ThemeData(primaryColor: primary, accentColor: accent),
      home: AppHome(),
    );
  }
}

class AppHome extends StatefulWidget {
  @override
  _AppHomeState createState() => _AppHomeState();
}

class _AppHomeState extends State<AppHome> with SingleTickerProviderStateMixin {
  TabController _tabController;

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 3, vsync: this, initialIndex: 0);
  }

  @override
  void dispose() {
    _tabController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      appBar: AppBar(
        title: Text("Aplicacao Ferias"),
        backgroundColor: Theme.of(context).primaryColor,
        bottom: TabBar(
          indicator: BoxDecoration(
            border: Border(
              bottom: BorderSide(
                color: Colors.white,
                width: 3,
              ),
            ),
          ),
          controller: _tabController,
          tabs: [
            Tab(text: "CALCULAR"),
            Tab(text: "GPS"),
            Tab(text: "CONTATOS"),
          ],
        ),
      ),
      body: TabBarView(
        controller: _tabController,
        children: [
          TabCalcular(),
          TabGPS(),
          TabContatos(),
        ],
      ),
    );
  }
}

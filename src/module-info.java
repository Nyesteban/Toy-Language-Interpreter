module main.java.com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.management;
    //requires eu.hansolo.tilesfx;
    //requires com.almasb.fxgl.all;

    //opens main.java.com.example.lab12 to javafx.fxml;
    //exports main.java.com.example.lab12;
    opens main to javafx.fxml;
    exports main;
    opens gui to javafx.fxml;
    exports gui;
    opens model.DTOs to javafx.base;
    exports model.DTOs;
    opens model.ADTs to javafx.base;
    exports model.ADTs;
    opens model.Expressions to javafx.base;
    exports model.Expressions;
    opens model.ProgramState to javafx.base;
    exports model.ProgramState;
    opens model.Statements to javafx.base;
    exports model.Statements;
    opens model.Types to javafx.base;
    exports model.Types;
    opens model.Values to javafx.base;
    exports model.Values;
    opens repository to javafx.fxml;
    exports repository;
    opens controller to javafx.fxml;
    exports controller;
}
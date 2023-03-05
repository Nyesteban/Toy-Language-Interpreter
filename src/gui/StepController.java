package gui;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import model.ADTs.*;
import model.DTOs.HeapEntry;
import model.DTOs.SymTableEntry;
import model.ProgramState.PrgState;
import model.Statements.IStmt;
import model.Values.*;
import view.RunExample;

import javax.management.loading.PrivateClassLoader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StepController {
    @FXML
    private Label heapTableLabel;
    @FXML
    private Label outLabel;
    @FXML
    private Label fileTableLabel;
    @FXML
    private Label programStatesLabel;
    @FXML
    private Label symbolTableLabel;
    @FXML
    private Label executionStackLabel;
    @FXML
    private Label nrOfProgramStatesLabel;
    private SelectController selectController;
    private RunExample selectedExample;
    private PrgState selectedProgram;

    @FXML
    private TextField nrOfPrgStatesTextField;

    @FXML
    private TableView<HeapEntry> heapTableView;
    @FXML
    private TableColumn<HeapEntry, String> addressColumn;
    @FXML
    private TableColumn<HeapEntry, String> valueHeapColumn;

    @FXML
    private ListView<Value> outListView;

    @FXML
    private ListView<StringValue> fileTableListView;

    @FXML
    private TableView<SymTableEntry> symbolTableView;
    @FXML
    private TableColumn<SymTableEntry, String> variableNameColumn;
    @FXML
    private TableColumn<SymTableEntry,String> valueSymColumn;

    @FXML
    private ListView<PrgState> prgStatesListView;

    @FXML
    private ListView<IStmt> exeStackListView;

    @FXML
    private Button runOneStepButton;

    private void showDataForSelectedExample(RunExample example){
        this.heapTableView.getItems().clear();
        this.outListView.getItems().clear();
        this.fileTableListView.getItems().clear();
        this.prgStatesListView.getItems().clear();
        this.symbolTableView.getItems().clear();
        this.exeStackListView.getItems().clear();

        List<PrgState> programStates = example.getController().getRepo().getProgramList();

        if(programStates.size() != 0)
            this.selectedProgram = programStates.get(0);

        MyIHeap<Integer, Value> sharedHeap = this.selectedProgram.getHeap();
        MyIDictionary<StringValue, BufferedReader> fileTable = this.selectedProgram.getFileTable();
        MyIList<Value> output = this.selectedProgram.getOutput();

        sharedHeap.getContent().forEach((address, value)->this.heapTableView.getItems().add(new HeapEntry(address, value)));
        fileTable.getContent().forEach((fileName, filePath)->this.fileTableListView.getItems().add(fileName));
        output.getAll().forEach((value)->this.outListView.getItems().add(value));

        programStates.forEach((programState)->this.prgStatesListView.getItems().add(programState));

        this.nrOfPrgStatesTextField.setText(Integer.toString(programStates.size()));

    }

    public void setSelectController(SelectController newSelectController){
        this.selectController = newSelectController;
        this.selectController.getExamplesListView().getSelectionModel().selectedItemProperty().addListener((a,b,ex)->this.showDataForSelectedExample(ex));
    }

    private void showDataForSelectedProgramState(PrgState program){
        this.symbolTableView.getItems().clear();
        this.exeStackListView.getItems().clear();

        MyIStack<IStmt> executionStack = program.getExecutionStack();
        MyIDictionary<String, Value> symbolTable = program.getSymbolTable();

        executionStack.get_stack().forEach((statement)->this.exeStackListView.getItems().add(statement));
        symbolTable.getContent().forEach((name, value)->this.symbolTableView.getItems().add(new SymTableEntry(name, value)));
    }

    private void runOneStep(RunExample ex){
        try{
            ex.getController().oneStepExecution();
        }
        catch (Exception ignored){

        }
        showDataForSelectedExample(ex);
    }

    @FXML
    private void initialize(){
        this.nrOfPrgStatesTextField.setEditable(false);

        this.addressColumn.setCellValueFactory(new PropertyValueFactory<HeapEntry, String>("heapAddress"));
        this.valueHeapColumn.setCellValueFactory(new PropertyValueFactory<HeapEntry, String >("heapValue"));

        this.variableNameColumn.setCellValueFactory(new PropertyValueFactory<SymTableEntry, String>("variableName"));
        this.valueSymColumn.setCellValueFactory(new PropertyValueFactory<SymTableEntry, String>("value"));

        this.outListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<Value>() {
            @Override
            public String toString(Value val) {
                return val.toString();
            }

            @Override
            public Value fromString(String s) {
                return null;
            }
        }));

        this.fileTableListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<StringValue>() {
            @Override
            public String toString(StringValue stringValue) {
                return stringValue.toString();
            }

            @Override
            public StringValue fromString(String s) {
                return null;
            }
        }));

        this.prgStatesListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<PrgState>() {
            @Override
            public String toString(PrgState programState) {
                return Integer.toString(programState.getId());
            }

            @Override
            public PrgState fromString(String s) {
                return null;
            }
        }));

        this.exeStackListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<IStmt>() {
            @Override
            public String toString(IStmt statementInterface) {
                return statementInterface.toString();
            }

            @Override
            public IStmt fromString(String s) {
                return null;
            }
        }));

        this.prgStatesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        this.prgStatesListView.getSelectionModel().selectedItemProperty().addListener((a,b,state)-> {
            if(state != null)
                showDataForSelectedProgramState(state);

        });

        this.runOneStepButton.setOnAction(actionEvent -> runOneStep(this.selectController.getExamplesListView().getSelectionModel().getSelectedItems().get(0)));

    }

}

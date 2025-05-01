public class Main {

    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("Πατήστε με");
        button.setOnAction(e -> System.out.println("Κουμπί πατήθηκε!"));

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setTitle("Η πρώτη σου JavaFX εφαρμογή");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

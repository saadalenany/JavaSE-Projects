/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationresearch.pert.input;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;

/**
 *
 * @author Amr
 */
public class input extends VBox {

    private ArrayList<edge> graph;
    
    private boolean haveTime;
     

    public input(boolean haveTime) {
        this.haveTime = haveTime;
        graph = new ArrayList<>();
        
        edge init = new edge(haveTime);
        graph.add(init);

        this.getChildren().add(init);
        this.setSpacing(10);
        EdgeControl();
    }

    private void EdgeControl() {
         new Timer(). scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                Platform.runLater(() -> {
                    edge last = graph.get(graph.size() - 1);
                    last.getActivity().setOnMouseClicked((MouseEvent e) -> {
                        if (last.getActivity() == graph.get(graph.size() - 1).getActivity()) {
                            edge newEdge = new edge(haveTime);
                            graph.add(newEdge);
                            getChildren().add(newEdge);
                        }
                        for (int i = 0; i < graph.size(); i++) {
                            System.out.println(graph.size());
                            if (graph.get(i).getActivity().getText().equals("") && i != graph.size() - 2 && i != graph.size() - 1) {
                                getChildren().remove(graph.get(i));
                                graph.remove(graph.get(i));
                            }
                        }
                    });
                });
            }
        }, 100, 100);
    }

    public ArrayList<edge> getGraph() {
        return graph;
    }

    public void setGraph(ArrayList<edge> graph) {
        this.graph = graph;
    }

}

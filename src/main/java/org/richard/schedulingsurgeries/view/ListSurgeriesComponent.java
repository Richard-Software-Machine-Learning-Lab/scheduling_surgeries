package org.richard.schedulingsurgeries.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListSurgeriesComponent extends JComponent {
    private Graphics2D graphics;
    private ArrayList<ListInformationSurgeriesView> listInformationSurgeriesView;

    public ListSurgeriesComponent(ArrayList<ListInformationSurgeriesView> listInformationSurgeriesView) {
        this.listInformationSurgeriesView = listInformationSurgeriesView;
    }

    @Override
    public void paintComponent(Graphics graphics){
        this.graphics = (Graphics2D) graphics;
        addLabelsSurgeries();
        addListSurgeriesInformation();
    }

    public void addLabelsSurgeries() {
        int xCoordinate = 10;
        int yCoordinate = 20;
        String[] labels = {"Surgery Id   ", "Patient Id  ", "Surgeon Id  ", "Speciality  ", "Procedure  ", "Duration  "};

        for (int label = 0; label < 4; label++) {
            String text = labels[label];
            graphics.drawString(text, xCoordinate, yCoordinate);
            xCoordinate = xCoordinate + 105;
        }
            xCoordinate = xCoordinate + 70;
        for (int label = 4; label < labels.length - 1; label++) {
            String text = labels[label];
            graphics.drawString(text, xCoordinate, yCoordinate);
            xCoordinate = xCoordinate + 350;
        }
            String text = labels[labels.length - 1];
            xCoordinate = xCoordinate + 150;
            graphics.drawString(text, xCoordinate, yCoordinate);
    }

    private void addListSurgeriesInformation() {
        double yOneCoordinate = 40;
        for (ListInformationSurgeriesView eachInformationSurgery: listInformationSurgeriesView) {
            graphics.drawString(Integer.toString(eachInformationSurgery.getSurgeryId()), 20, (float) yOneCoordinate);
            graphics.drawString(Integer.toString(eachInformationSurgery.getPatientId()), 120, (float) yOneCoordinate);
            graphics.drawString(Integer.toString(eachInformationSurgery.getSurgeonId()), 220, (float) yOneCoordinate);
            graphics.drawString(eachInformationSurgery.getSpecialityName(), 320, (float) yOneCoordinate);
            System.out.println(eachInformationSurgery.getProcedure());
            graphics.drawString(eachInformationSurgery.getProcedure().trim(), 500, (float) yOneCoordinate);
            graphics.drawString(Integer.toString(eachInformationSurgery.getDuration()), 1000, (float) yOneCoordinate);
            yOneCoordinate = yOneCoordinate + 60;
        }
    }

}

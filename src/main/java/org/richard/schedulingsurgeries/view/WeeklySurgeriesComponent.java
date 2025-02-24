package org.richard.schedulingsurgeries.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class WeeklySurgeriesComponent extends JComponent {
    private ArrayList<SurgeryView> surgeryViews;
    private static final LocalDateTime START_WEEK = LocalDateTime.of(2019, 11, 4, 7, 30);
    private static final LocalDateTime FINAL_WEEK = LocalDateTime.of(2019, 11, 8, 20, 30);
    private LocalDate openingDateOR;
    private LocalDate closingDateOR;
    private LocalTime openingTimeOR;
    private LocalTime closingTimeOR;
    private static final int HEIGHT_DAYS = 50;
    private static final int COLUMN_TIME_WIDTH = 60;
    private double scaleSchedule;
    private double widthOfTheDay;
    private Graphics2D graphics;

    public WeeklySurgeriesComponent(ArrayList<SurgeryView> surgeryViews) {
        this.surgeryViews = surgeryViews;
        openingDateOR = LocalDate.of(START_WEEK.getYear(), START_WEEK.getMonth(), START_WEEK.getDayOfMonth());
        closingDateOR = LocalDate.of(FINAL_WEEK.getYear(), FINAL_WEEK.getMonth(), FINAL_WEEK.getDayOfMonth());
        openingTimeOR = LocalTime.of(START_WEEK.getHour(), START_WEEK.getMinute());
        closingTimeOR = LocalTime.of(FINAL_WEEK.getHour(), FINAL_WEEK.getMinute());

    }

    @Override
    public void paintComponent(Graphics graphics){
        scaleSchedule = 0.016;
        widthOfTheDay = 182;
        this.graphics = (Graphics2D) graphics;
        addDayHeaders();
        addLinesSchedule();
        addTimes();
        addSurgeries();
    }

    public void addDayHeaders() {
        int xCoordinate = 110;
        int yCoordinate = 20;
        LocalDate openingDate = openingDateOR;
        for (LocalDate date = openingDate; date.isBefore(closingDateOR.plusDays(1)); date = date.plusDays(1)) {
            String text = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.UK) + " " + date.getDayOfMonth() + "/" + date.getMonth().getValue();
            graphics.drawString(text, xCoordinate, yCoordinate);
            xCoordinate = xCoordinate + 190;
        }
    }

    public void addLinesSchedule() {
        LocalDate openingDate = openingDateOR;
        double xCoordinate = 0;
        for (LocalDate date = openingDate; date.isBefore(closingDateOR.plusDays(1)); date = date.plusDays(1)) {
            xCoordinate = getWidthOfEachDay(date.getDayOfWeek().getValue()) + 5;
            this.graphics.draw(new Line2D.Double(xCoordinate, HEIGHT_DAYS, xCoordinate, this.getHeightTime(closingTimeOR.toSecondOfDay())));
        }
    }

    public void addTimes() {
        int yCoordinate = 0;
        for (LocalTime date = openingTimeOR; date.isBefore(closingTimeOR.plusHours(1)); date = date.plusHours(1)) {
            yCoordinate = (int) getHeightTime(date.toSecondOfDay());
            this.graphics.drawString(date.toString(), 30, yCoordinate);
        }
    }

    private double getWidthOfEachDay(int dayValue) {
        return COLUMN_TIME_WIDTH + this.getWidthOfTheDay() * (dayValue - 1);
    }

    private void addSurgeries() {
        double xCoordinate;
        double yOneCoordinate;
        double yTwoCoordinate;
        for (SurgeryView eachSurgery: surgeryViews) {
            xCoordinate = this.getWidthOfEachDay(eachSurgery.getStartingTimeSurgery().getDayOfWeek().getValue()) + 10;
            yOneCoordinate = this.getHeightTime(((eachSurgery.getStartingTimeSurgery().getHour() * 60) + eachSurgery.getStartingTimeSurgery().getMinute()) * 60);
            yTwoCoordinate =  this.getHeightTime(((eachSurgery.getFinishingTimeSurgery().getHour() * 60) + eachSurgery.getFinishingTimeSurgery().getMinute()) * 60);
            Rectangle2D eachRectangleSurgery = new Rectangle2D.Double(xCoordinate, yOneCoordinate,
                    this.getWidthOfTheDay() - 10, yTwoCoordinate - yOneCoordinate);
            Color originalColor = graphics.getColor();
            graphics.setColor(eachSurgery.getColor());
            graphics.fill(eachRectangleSurgery);
            graphics.setColor(originalColor);
            StringBuilder startFinishSurgery = new StringBuilder();
            startFinishSurgery.append(eachSurgery.getStartingTimeSurgery().format(DateTimeFormatter.ofPattern("HH:mm")));
            startFinishSurgery.append("-");
            startFinishSurgery.append(eachSurgery.getFinishingTimeSurgery().format(DateTimeFormatter.ofPattern("HH:mm")));
            graphics.drawString(startFinishSurgery.toString(), (int) xCoordinate + 10, (int) yOneCoordinate + 15);
            graphics.drawString(eachSurgery.getText(), (int) xCoordinate + 100, (int) yOneCoordinate + 15);
        }
    }

    public double getWidthOfTheDay() {
        return widthOfTheDay;
    }

    private double getHeightTime(int seconds) {
        return HEIGHT_DAYS + ((seconds - openingTimeOR.toSecondOfDay()) * scaleSchedule);
    }


}
package org.richard.schedulingsurgeries.view;

import java.awt.*;
import java.time.LocalDateTime;

public class SurgeryView {
    private static final Color DEFAULT_COLOR = Color.orange;
    private LocalDateTime startingTimeSurgery;
    private LocalDateTime finishingTimeSurgery;
    private String text;
    private Color color;

    public SurgeryView(LocalDateTime startingTimeSurgery, LocalDateTime finishingTimeSurgery, String text) {
        this.startingTimeSurgery = startingTimeSurgery;
        this.finishingTimeSurgery = finishingTimeSurgery;
        this.text = text;
        this.color = DEFAULT_COLOR;
    }

    public LocalDateTime getStartingTimeSurgery() {
        return startingTimeSurgery;
    }

    public void setStartingTimeSurgery(LocalDateTime startingTimeSurgery) {
        this.startingTimeSurgery = startingTimeSurgery;
    }

    public LocalDateTime getFinishingTimeSurgery() {
        return finishingTimeSurgery;
    }

    public void setFinishingTimeSurgery(LocalDateTime finishingTimeSurgery) {
        this.finishingTimeSurgery = finishingTimeSurgery;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}


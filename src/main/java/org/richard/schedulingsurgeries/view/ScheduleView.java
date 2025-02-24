package org.richard.schedulingsurgeries.view;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScheduleView {
    private static int DEFAULT_FRAME_WIDTH = 1000;
    private static int DEFAULT_FRAME_HEIGHT = 900;
    private JFrame mainFrame;
    private JPanel panelButtons;
    private JTabbedPane tabsOperatingRooms;
    private JPanel panelWelcome;
    private JLabel welcomeMessage;
    private JPanel operatingRoomsInformation;
    private JPanel panelAddSurgeries;
    private ListSurgeriesComponent listSurgeriesComponent;
    private JScrollPane scrollPanelSurgeries;
    private AddSurgeryComponent addSurgeryPanel;
    protected static String BUTTON_ADD_SURGERIES = "ADD SURGERIES";
    protected static String BUTTON_ADD_INFORMATION_SURGERY = "ADD INFORMATION SURGERY";
    protected static String BUTTON_VIEW_SURGERIES = "VIEW SURGERIES";
    protected static String BUTTON_SCHEDULE_SURGERIES = "SCHEDULE SURGERIES";
    protected static String BUTTON_VIEW_SCHEDULE = "VIEW SCHEDULE";
    private JButton addSurgeriesButton;
    private JButton viewListSurgeriesButton;
    private JButton scheduleSurgeriesButton;
    private JButton viewScheduleButton;

    private RequestHandler requestHandler;
    private HashMap<Integer, ArrayList> scheduledSurgeriesMap;
    private HashMap<Integer, ArrayList> listSurgeriesMap;

    public ScheduleView() {
        createMainFrame();
        createPanelButtons();
        createPanelWelcome();
        createViews();
        mainFrame.add(panelButtons, BorderLayout.NORTH);
        mainFrame.add(panelWelcome, BorderLayout.CENTER);
        mainFrame.setResizable(false);
        mainFrame.pack();

        requestHandler = new RequestHandler();
    }

    public void addTabRooms() throws IOException {
        mainFrame.remove(panelWelcome);
        mainFrame.remove(scrollPanelSurgeries);
        mainFrame.remove(panelAddSurgeries);
        mainFrame.remove(addSurgeryPanel);
        int numberOfRooms = requestHandler.getNumberOperatingRooms();
        String response = requestHandler.getSolution();
        this.parseRoomsJson(response);
        tabsOperatingRooms.removeAll();
        operatingRoomsInformation.removeAll();
        tabsOperatingRooms.setPreferredSize(new Dimension(1000, 850));
        operatingRoomsInformation.setPreferredSize(new Dimension(1000, 100));
        for (int roomNumber = 1; roomNumber <= numberOfRooms; roomNumber++){
            ArrayList<SurgeryView> surgeriesView = new ArrayList<>();
            for (Map.Entry<Integer, ArrayList> entry : scheduledSurgeriesMap.entrySet()) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                int key = entry.getKey();
                ArrayList value = entry.getValue();
                if (((int) value.get(0)) == roomNumber) {
                    LocalDateTime openingOperatingRoom = LocalDateTime.parse(value.get(2).toString(), format).truncatedTo(ChronoUnit.MINUTES);
                    LocalDateTime closingOperationRoom = LocalDateTime.parse(value.get(3).toString(), format).truncatedTo(ChronoUnit.MINUTES);
                    surgeriesView.add(new SurgeryView(openingOperatingRoom, closingOperationRoom, "Surgery " + key));
                }
            }
            JComponent operatingRoom = new WeeklySurgeriesComponent(surgeriesView);
            tabsOperatingRooms.add("OR " + roomNumber, operatingRoom);
        }
        mainFrame.add(tabsOperatingRooms,BorderLayout.CENTER);
        mainFrame.add(operatingRoomsInformation, BorderLayout.SOUTH);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void createViews() {
        tabsOperatingRooms  = new JTabbedPane();
        operatingRoomsInformation = new JPanel();
        scrollPanelSurgeries = new JScrollPane();
        panelAddSurgeries = new JPanel();
        addSurgeryPanel = new AddSurgeryComponent();
    }

    public void addListSurgeries() throws IOException {
        mainFrame.remove(panelWelcome);
        mainFrame.remove(tabsOperatingRooms);
        mainFrame.remove(operatingRoomsInformation);
        mainFrame.remove(panelAddSurgeries);
        mainFrame.remove(addSurgeryPanel);

        String response = requestHandler.getSurgeries();
        this.parseInformationSurgeriesJson(response);
        ArrayList<ListInformationSurgeriesView> listInformationSurgeriesView = new ArrayList<>();
        for (Map.Entry<Integer, ArrayList> entry : listSurgeriesMap.entrySet()) {
            int surgeryId = entry.getKey();
            ArrayList values = entry.getValue();
            listInformationSurgeriesView.add(new ListInformationSurgeriesView(surgeryId, (int) values.get(0),
                                      (int) values.get(1), values.get(2).toString(), values.get(3).toString(), (int) values.get(4)));
        }
        listSurgeriesComponent = new ListSurgeriesComponent(listInformationSurgeriesView);
        listSurgeriesComponent.setPreferredSize(new Dimension(1200, 3000));
        scrollPanelSurgeries = new JScrollPane(listSurgeriesComponent);

        mainFrame.add(scrollPanelSurgeries);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void addSurgeryInformation() throws IOException {
        mainFrame.remove(panelWelcome);
        mainFrame.remove(tabsOperatingRooms);
        mainFrame.remove(operatingRoomsInformation);
        mainFrame.remove(scrollPanelSurgeries);
        addSurgeryPanel.removeAll();
        addSurgeryPanel.addInformation(requestHandler);
        addSurgeryPanel.setPreferredSize(new Dimension(1000, 750));
        mainFrame.add(addSurgeryPanel, BorderLayout.CENTER);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void parseRoomsJson(String responseJSON) {
        scheduledSurgeriesMap = new HashMap<>();
        JSONObject timeTable = new JSONObject(responseJSON);
        JSONArray scheduledSurgeries = timeTable.getJSONArray("surgeries");
        for (int counter = 0; counter < scheduledSurgeries.length(); counter ++){
            ArrayList information = new ArrayList();
            JSONObject scheduledSurgery = scheduledSurgeries.getJSONObject(counter);
            JSONObject operatingRoom = scheduledSurgery.getJSONObject("operatingRoom");
            int scheduledSurgeryId = scheduledSurgery.getInt("surgeryId");
            System.out.println(scheduledSurgeryId);
            int operatingRoomId = operatingRoom.getInt("operatingRoomId");
            System.out.println(operatingRoomId);
            String operatingRoomName = operatingRoom.getString("operatingRoomName");
            information.add(operatingRoomId);
            information.add(operatingRoomName);
            System.out.println(operatingRoomName);
            String startingTimeSurgery = scheduledSurgery.getString("startingTimeSurgery");
            System.out.println(startingTimeSurgery);
            information.add(startingTimeSurgery);
            String finishingTimeSurgery = scheduledSurgery.getString("finishingTimeSurgery");
            System.out.println(finishingTimeSurgery);
            information.add(finishingTimeSurgery);
            scheduledSurgeriesMap.put(scheduledSurgeryId, information);
        }
    }

    public void parseInformationSurgeriesJson(String responseJSON) {
        listSurgeriesMap = new HashMap<>();
        JSONArray surgeries = new JSONArray(responseJSON);
        for (int counter = 0; counter < surgeries.length(); counter ++) {
            ArrayList information = new ArrayList();
            JSONObject surgery = surgeries.getJSONObject(counter);
            int surgeryId = surgery.getInt("surgeryId");
            int patientId = surgery.getJSONObject("patient").getInt("patientId");
            JSONObject surgeon = surgery.getJSONObject("surgeon");
            int surgeonId = surgeon.getInt("surgeonId");
            String specialityName = surgeon.getJSONObject("speciality").getString("name");
            String procedure = surgery.getJSONObject("procedure").getString("name");
            int duration = surgery.getInt("surgeryDuration");
            information.add(patientId);
            information.add(surgeonId);
            information.add(specialityName);
            information.add(procedure);
            information.add(duration);
            listSurgeriesMap.put(surgeryId, information);
        }
    }

    public void createMainFrame() {
        mainFrame = new JFrame("SCHEDULING SURGERIES");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        mainFrame.setVisible(true);
    }

    public void createPanelWelcome() {
        welcomeMessage = new JLabel();
        welcomeMessage.setText("WELCOME TO THE SCHEDULING HEALTHCARE SYSTEM");
        welcomeMessage.setFont(new java.awt.Font("Arial", Font.ITALIC, 20));
        welcomeMessage.setOpaque(true);
        welcomeMessage.setBackground(Color.WHITE);
        welcomeMessage.setForeground(Color.BLUE);
        panelWelcome = new JPanel();
        panelWelcome.setPreferredSize(new Dimension(1000, 850));
        panelWelcome.add(welcomeMessage);
    }

    public void createPanelButtons() {
        addSurgeriesButton = new JButton(BUTTON_ADD_SURGERIES);
        viewListSurgeriesButton = new JButton(BUTTON_VIEW_SURGERIES);
        scheduleSurgeriesButton = new JButton(BUTTON_SCHEDULE_SURGERIES);
        viewScheduleButton = new JButton(BUTTON_VIEW_SCHEDULE);
        viewScheduleButton.setEnabled(false);
        panelButtons = new JPanel();
        panelButtons.setPreferredSize(new Dimension(1000, 40));
        panelButtons.setBackground(Color.gray);
        panelButtons.add(addSurgeriesButton);
        panelButtons.add(viewListSurgeriesButton);
        panelButtons.add(scheduleSurgeriesButton);
        panelButtons.add(viewScheduleButton);
        addButtonsListener();
    }

    public void addButtonsListener() {

        addSurgeriesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    addSurgeryInformation();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        viewListSurgeriesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    addListSurgeries();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        scheduleSurgeriesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    requestHandler.solve();
                    viewScheduleButton.setEnabled(true);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        viewScheduleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    addTabRooms();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}

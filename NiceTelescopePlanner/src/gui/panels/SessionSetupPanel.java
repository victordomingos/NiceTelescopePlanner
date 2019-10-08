/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.panels;

import Constants.LIM_MAGNITUDE;
import core.Location;
import db.DbConnection;
import gui.Main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author victor
 */
public class SessionSetupPanel extends javax.swing.JPanel {

    private DbConnection mydb = new DbConnection();
    private Location curSelectedLocation = null;
    private Main master;

    /**
     * Creates new form SessionSetupPanel
     *
     * @param master
     */
    public SessionSetupPanel(Main master) {
        initComponents();
        this.master = master;

        // Populate limiting magnitude combobox -----------
        date_picker.setDate(new Date());
        DefaultComboBoxModel dlm = new DefaultComboBoxModel(LIM_MAGNITUDE.values());
        cmb_limitingMagnitude.setModel(dlm);

        // TODO: remove this line after the first MVP release ;)
        subpanel_telescopeAngles.setVisible(false);
        updateLocationsCombo();
    }

    public void updateLocationsCombo() {
        // Populate locations combobox --------------------
        ArrayList<Location> locs = mydb.getAllLocations();
        DefaultComboBoxModel dlocm = new DefaultComboBoxModel();

        for (Location loc : locs) {
            dlocm.addElement(Integer.toString(loc.getId()) + " - " + loc.getName());
        }
        this.cmb_location.setModel(dlocm);
    }

    /**
     * Get the date and time selected in the date picker and start time spinbox.
     *
     * @return a LocalDateTime object
     */
    public LocalDateTime getStartDatetime() {
        Date startDate = this.date_picker.getDate();
        String startHour = cmb_startTime.getSelectedItem().toString();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strData = dateFormat.format(startDate);
        strData = strData + " " + startHour;
        
        DateTimeFormatter dtformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(strData, dtformatter);
    }

    /**
     * Get the session end date and time from date picker and end time spinbox.
     *
     * If start time is later in the day than the end time, it means the session
     * end date is in the next day, so the returned calendar it will be set to
     * the date selected in the date picker date +1 day.
     *
     * @return a Calendar object
     */
    public LocalDateTime getEndDatetime() {
        Date endDate = this.date_picker.getDate();
        String endHour = cmb_endTime.getSelectedItem().toString();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strEndDate = dateFormat.format(endDate);
        strEndDate = strEndDate + " " + endHour;
        
        DateTimeFormatter dtformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime endDateL = LocalDateTime.parse(strEndDate, dtformatter);
        
        endDateL = LocalDateTime.parse(strEndDate, dtformatter);
        
        if (endDateL.getHour() <= getStartDatetime().getHour()) {
            endDateL = endDateL.plusDays(1);
        }

        return endDateL;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftPanel = new org.jdesktop.swingx.JXPanel();
        jLabel10 = new javax.swing.JLabel();
        subpanel_location = new org.jdesktop.swingx.JXPanel();
        cmb_location = new javax.swing.JComboBox<>();
        btn_applySessionSettings = new javax.swing.JButton();
        subpanel_dateTime = new org.jdesktop.swingx.JXPanel();
        jLabel20 = new javax.swing.JLabel();
        btn_SetToTwilightTime1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        date_picker = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new javax.swing.JLabel();
        cmb_startTime = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmb_endTime = new javax.swing.JComboBox<>();
        subpanel_telescopeAngles = new org.jdesktop.swingx.JXPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        spin_altStart = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        spin_altEnd = new javax.swing.JSpinner();
        jLabel15 = new javax.swing.JLabel();
        spin_azStart = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        spin_azEnd = new javax.swing.JSpinner();
        btn_SetToTwilightTime = new javax.swing.JButton();
        subpanel_filterSugestions = new org.jdesktop.swingx.JXPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cmb_kind = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        cmb_catalog = new javax.swing.JComboBox<>();
        cmb_limitingMagnitude = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        spin_limitingMagnitude = new javax.swing.JSpinner();
        slider_limitingMagnitude = new javax.swing.JSlider();

        setMaximumSize(new java.awt.Dimension(350, 32767));
        setPreferredSize(new java.awt.Dimension(350, 700));

        leftPanel.setBackground(javax.swing.UIManager.getDefaults().getColor("Nb.browser.picker.background.light"));
        leftPanel.setPreferredSize(new java.awt.Dimension(350, 580));

        jLabel10.setBackground(javax.swing.UIManager.getDefaults().getColor("Nb.browser.picker.background.light"));
        jLabel10.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Session setup");

        subpanel_location.setPreferredSize(new java.awt.Dimension(340, 100));

        cmb_location.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select observatory/location" }));
        cmb_location.setName("cmb_location"); // NOI18N
        cmb_location.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_locationItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout subpanel_locationLayout = new javax.swing.GroupLayout(subpanel_location);
        subpanel_location.setLayout(subpanel_locationLayout);
        subpanel_locationLayout.setHorizontalGroup(
            subpanel_locationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subpanel_locationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmb_location, 0, 325, Short.MAX_VALUE)
                .addContainerGap())
        );
        subpanel_locationLayout.setVerticalGroup(
            subpanel_locationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subpanel_locationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmb_location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_applySessionSettings.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        btn_applySessionSettings.setText("Apply these settings");
        btn_applySessionSettings.setName("btn_applySessionSettings"); // NOI18N
        btn_applySessionSettings.setPreferredSize(new java.awt.Dimension(260, 29));
        btn_applySessionSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_applySessionSettingsActionPerformed(evt);
            }
        });

        subpanel_dateTime.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        subpanel_dateTime.setPreferredSize(new java.awt.Dimension(334, 100));

        jLabel20.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel20.setText("Date and time");

        btn_SetToTwilightTime1.setText("Set to astronomical twilight");
        btn_SetToTwilightTime1.setName("btn_SetToTwilightTime"); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Date:");
        jLabel2.setMinimumSize(new java.awt.Dimension(105, 16));
        jLabel2.setPreferredSize(new java.awt.Dimension(105, 16));

        date_picker.setToolTipText("The day of the observation being planned. Most likely, it will be during nght time, so please specify the day corresponding to the session start date.");
        date_picker.setName("date_picker"); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Time (hh):");
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 16));

        cmb_startTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00" }));
        cmb_startTime.setSelectedIndex(22);
        cmb_startTime.setName("cmb_startTime"); // NOI18N

        jLabel4.setText("to");

        cmb_endTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00" }));
        cmb_endTime.setSelectedIndex(3);
        cmb_endTime.setName("cmb_endTime"); // NOI18N

        javax.swing.GroupLayout subpanel_dateTimeLayout = new javax.swing.GroupLayout(subpanel_dateTime);
        subpanel_dateTime.setLayout(subpanel_dateTimeLayout);
        subpanel_dateTimeLayout.setHorizontalGroup(
            subpanel_dateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subpanel_dateTimeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(subpanel_dateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(subpanel_dateTimeLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subpanel_dateTimeLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(subpanel_dateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(subpanel_dateTimeLayout.createSequentialGroup()
                                .addGroup(subpanel_dateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(subpanel_dateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(date_picker, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(subpanel_dateTimeLayout.createSequentialGroup()
                                        .addComponent(cmb_startTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmb_endTime, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(btn_SetToTwilightTime1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))))
        );
        subpanel_dateTimeLayout.setVerticalGroup(
            subpanel_dateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subpanel_dateTimeLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel20)
                .addGap(10, 10, 10)
                .addGroup(subpanel_dateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_picker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(subpanel_dateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_startTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cmb_endTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_SetToTwilightTime1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        subpanel_telescopeAngles.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        subpanel_telescopeAngles.setPreferredSize(new java.awt.Dimension(334, 161));

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel11.setText("Telescope Angles");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Altitude range:");

        spin_altStart.setModel(new javax.swing.SpinnerNumberModel(5.0d, -90.0d, 90.0d, 1.0d));
        spin_altStart.setName("spin_altStart"); // NOI18N

        jLabel13.setText("to");

        spin_altEnd.setName("spin_altEnd"); // NOI18N

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Azimuth range:");

        spin_azStart.setName("spin_azStart"); // NOI18N

        jLabel14.setText("to");

        spin_azEnd.setName("spin_azEnd"); // NOI18N

        btn_SetToTwilightTime.setText("Set to 5º above the horizon");
        btn_SetToTwilightTime.setName("btn_SetToTwilightTime"); // NOI18N

        javax.swing.GroupLayout subpanel_telescopeAnglesLayout = new javax.swing.GroupLayout(subpanel_telescopeAngles);
        subpanel_telescopeAngles.setLayout(subpanel_telescopeAnglesLayout);
        subpanel_telescopeAnglesLayout.setHorizontalGroup(
            subpanel_telescopeAnglesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subpanel_telescopeAnglesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(subpanel_telescopeAnglesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(subpanel_telescopeAnglesLayout.createSequentialGroup()
                        .addGroup(subpanel_telescopeAnglesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(subpanel_telescopeAnglesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(subpanel_telescopeAnglesLayout.createSequentialGroup()
                                .addComponent(spin_azStart, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spin_azEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(subpanel_telescopeAnglesLayout.createSequentialGroup()
                                .addComponent(spin_altStart, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spin_altEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subpanel_telescopeAnglesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_SetToTwilightTime, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        subpanel_telescopeAnglesLayout.setVerticalGroup(
            subpanel_telescopeAnglesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subpanel_telescopeAnglesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel11)
                .addGap(10, 10, 10)
                .addGroup(subpanel_telescopeAnglesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(spin_altStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spin_altEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(subpanel_telescopeAnglesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spin_azStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(spin_azEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_SetToTwilightTime)
                .addGap(4, 4, 4))
        );

        subpanel_filterSugestions.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        subpanel_filterSugestions.setPreferredSize(new java.awt.Dimension(334, 153));

        jLabel17.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel17.setText("Filter sugestions by");

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Kind:");

        cmb_kind.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmb_kind.setName("cmb_kind"); // NOI18N

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Catalog:");

        cmb_catalog.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmb_catalog.setName("cmb_catalog"); // NOI18N

        cmb_limitingMagnitude.setToolTipText("The apparent magnitude of the faintest object that is visible with the naked-eye or a telescope.");
        cmb_limitingMagnitude.setName("cmb_kind"); // NOI18N
        cmb_limitingMagnitude.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_limitingMagnitudeActionPerformed(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Lim. Magnitude:");

        spin_limitingMagnitude.setModel(new javax.swing.SpinnerNumberModel(15, -27, 23, 1));
        spin_limitingMagnitude.setToolTipText("The apparent magnitude of the faintest object that is visible with the naked-eye or a telescope.");
        spin_limitingMagnitude.setName("spin_limitingMagnitude"); // NOI18N
        spin_limitingMagnitude.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spin_limitingMagnitudeStateChanged(evt);
            }
        });
        spin_limitingMagnitude.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spin_limitingMagnitudePropertyChange(evt);
            }
        });

        slider_limitingMagnitude.setMajorTickSpacing(10);
        slider_limitingMagnitude.setMaximum(23);
        slider_limitingMagnitude.setMinimum(-27);
        slider_limitingMagnitude.setMinorTickSpacing(2);
        slider_limitingMagnitude.setPaintTicks(true);
        slider_limitingMagnitude.setToolTipText("The apparent magnitude of the faintest object that is visible with the naked-eye or a telescope.");
        slider_limitingMagnitude.setValue(15);
        slider_limitingMagnitude.setName("slider_limitingMagnitude"); // NOI18N
        slider_limitingMagnitude.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                slider_limitingMagnitudeStateChanged(evt);
            }
        });

        javax.swing.GroupLayout subpanel_filterSugestionsLayout = new javax.swing.GroupLayout(subpanel_filterSugestions);
        subpanel_filterSugestions.setLayout(subpanel_filterSugestionsLayout);
        subpanel_filterSugestionsLayout.setHorizontalGroup(
            subpanel_filterSugestionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subpanel_filterSugestionsLayout.createSequentialGroup()
                .addGroup(subpanel_filterSugestionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(subpanel_filterSugestionsLayout.createSequentialGroup()
                        .addGroup(subpanel_filterSugestionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(subpanel_filterSugestionsLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(subpanel_filterSugestionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subpanel_filterSugestionsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(subpanel_filterSugestionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmb_catalog, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb_kind, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(subpanel_filterSugestionsLayout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(spin_limitingMagnitude, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(subpanel_filterSugestionsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(subpanel_filterSugestionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(cmb_limitingMagnitude, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(slider_limitingMagnitude, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(15, 15, 15))
        );
        subpanel_filterSugestionsLayout.setVerticalGroup(
            subpanel_filterSugestionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subpanel_filterSugestionsLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel17)
                .addGap(5, 5, 5)
                .addGroup(subpanel_filterSugestionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(spin_limitingMagnitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slider_limitingMagnitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_limitingMagnitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(subpanel_filterSugestionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_kind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(subpanel_filterSugestionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_catalog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(subpanel_location, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(leftPanelLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(btn_applySessionSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(leftPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(leftPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(subpanel_dateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(leftPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(subpanel_telescopeAngles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(subpanel_filterSugestions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(subpanel_location, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subpanel_dateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subpanel_telescopeAngles, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subpanel_filterSugestions, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btn_applySessionSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_limitingMagnitudeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_limitingMagnitudeActionPerformed
        String limit = cmb_limitingMagnitude.getSelectedItem().toString();
        spin_limitingMagnitude.setValue(LIM_MAGNITUDE.get(limit).getLimit());
    }//GEN-LAST:event_cmb_limitingMagnitudeActionPerformed

    private void cmb_locationStateChanged(java.awt.event.ActionEvent evt) {

    }


    private void spin_limitingMagnitudePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spin_limitingMagnitudePropertyChange

    }//GEN-LAST:event_spin_limitingMagnitudePropertyChange

    private void slider_limitingMagnitudeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_slider_limitingMagnitudeStateChanged
        spin_limitingMagnitude.setValue(slider_limitingMagnitude.getValue());
    }//GEN-LAST:event_slider_limitingMagnitudeStateChanged

    private void spin_limitingMagnitudeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spin_limitingMagnitudeStateChanged
        this.slider_limitingMagnitude.setValue((int) spin_limitingMagnitude.getValue());
// TODO add your handling code here:    }//GEN-LAST:event_spin_limitingMagnitudeStateChanged
    }

    private void cmb_locationItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_locationItemStateChanged
        String selectedLocName = cmb_location.getSelectedItem().toString();
        int sep = selectedLocName.indexOf(" - ");
        int id = Integer.parseInt(selectedLocName.substring(0, sep));
        this.setCurSelectedLocation(mydb.getOneLocation(id));
    }//GEN-LAST:event_cmb_locationItemStateChanged

    private void btn_applySessionSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_applySessionSettingsActionPerformed
        this.master.applySessionSettings();
    }//GEN-LAST:event_btn_applySessionSettingsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_SetToTwilightTime;
    private javax.swing.JButton btn_SetToTwilightTime1;
    private javax.swing.JButton btn_applySessionSettings;
    private javax.swing.JComboBox<String> cmb_catalog;
    private javax.swing.JComboBox<String> cmb_endTime;
    private javax.swing.JComboBox<String> cmb_kind;
    private javax.swing.JComboBox<String> cmb_limitingMagnitude;
    private javax.swing.JComboBox<String> cmb_location;
    private javax.swing.JComboBox<String> cmb_startTime;
    private org.jdesktop.swingx.JXDatePicker date_picker;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private org.jdesktop.swingx.JXPanel leftPanel;
    private javax.swing.JSlider slider_limitingMagnitude;
    private javax.swing.JSpinner spin_altEnd;
    private javax.swing.JSpinner spin_altStart;
    private javax.swing.JSpinner spin_azEnd;
    private javax.swing.JSpinner spin_azStart;
    private javax.swing.JSpinner spin_limitingMagnitude;
    private org.jdesktop.swingx.JXPanel subpanel_dateTime;
    private org.jdesktop.swingx.JXPanel subpanel_filterSugestions;
    private org.jdesktop.swingx.JXPanel subpanel_location;
    private org.jdesktop.swingx.JXPanel subpanel_telescopeAngles;
    // End of variables declaration//GEN-END:variables

    public Location getCurSelectedLocation() {
        return curSelectedLocation;
    }

    public void setCurSelectedLocation(Location curSelectedLocation) {
        this.curSelectedLocation = curSelectedLocation;
    }

    public javax.swing.JButton getBtn_applySessionSettings() {
        return btn_applySessionSettings;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import core.Location;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jparsec.math.Constant;
import jparsec.observer.City;
import jparsec.observer.CityElement;
import jparsec.observer.Country;
import jparsec.observer.Country.COUNTRY;
import jparsec.observer.Observatory;
import jparsec.observer.ObservatoryElement;
import jparsec.util.JPARSECException;

/**
 *
 * @author victordomingos
 */
public class LocationManager extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    private final db.DbConnection mydb = new db.DbConnection();
    // -1 means form empty or editing a new session; 
    // Any other number means the db ID of an existing location being edited/shown.
    private int curLocationBeingEdited = -1;
    private Location newLocation;

    /**
     * Creates new form Sessions
     */
    public LocationManager() {
        initComponents();
        updateTable();

        if (table.getRowCount() == 0) {
            this.centerBottomPanel.setVisible(true);
            this.btn_locationDetails.setSelected(true);
        } else {
            this.centerBottomPanel.setVisible(false);
            this.btn_locationDetails.setSelected(false);
        }

        String btn_text = this.btn_locationDetails.isSelected() ? "Hide Location Details" : "Show Location Details";
        this.btn_locationDetails.setText(btn_text);


        this.addComponentListener(new ComponentAdapter() {
//            public void componentHidden(ComponentEvent e) {
//                /* code run when component hidden*/
//            }

            @Override
            public void componentShown(ComponentEvent e) {
                updateTable();
            }
        });

        initCombos();

    }

    private void updateTable() {
        Double lat_rad, lat_deg, lon_rad, lon_deg, timezone;
        String lat, lon, height;
        String tz_formatted = "";

        // get all locations from the database
        ArrayList<Location> allLocations = mydb.getAllLocations();

        //Set columns headers and table Model - make it non-editable
        String columns[] = {"ID", "Location name", "Latitude", "Longitude", "Altitude", "Timezone Offset"};

        DefaultTableModel LocationsTableModel = new DefaultTableModel(columns, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };

        table.setModel(LocationsTableModel);

        // add location records to table
        for (int i = 0; i < allLocations.size(); i++) {
            String id = Integer.toString(allLocations.get(i).getId());
            String name = allLocations.get(i).getName();

            lat_rad = allLocations.get(i).getLatitudeRad();
            lat_deg = lat_rad * Constant.RAD_TO_DEG;
            lat = String.format("%.6f", lat_deg);
            lat = lat.substring(0, Math.min(lat.length(), 8));

            lon_rad = allLocations.get(i).getLongitudeRad();
            lon_deg = lon_rad * Constant.RAD_TO_DEG;
            lon = String.format("%.6f", lon_deg);
            lon = lon.substring(0, Math.min(lon.length(), 8));

            height = Integer.toString(allLocations.get(i).getHeight());

            timezone = allLocations.get(i).getTimeZoneOffset();
            //tz_formatted = String.format("%.6f", timezone);
            //tz_formatted = tz_formatted.substring(0, Math.min(tz_formatted.length(), 6));
            tz_formatted = timezone.toString();

            Object[] data = {id, name, lat, lon, height, tz_formatted};
            LocationsTableModel.addRow(data);
        }

        // Set basic table formatting
        table.getColumn("ID").setMaxWidth(50);
        table.getColumn("Latitude").setMaxWidth(160);
        table.getColumn("Longitude").setMaxWidth(160);
        table.getColumn("Altitude").setMaxWidth(80);
        table.getColumn("Timezone Offset").setMaxWidth(80);
        table.getColumn("ID").setPreferredWidth(50);
        table.getColumn("Latitude").setPreferredWidth(160);
        table.getColumn("Longitude").setPreferredWidth(160);
        table.getColumn("Altitude").setPreferredWidth(80);
        table.getColumn("Timezone Offset").setPreferredWidth(80);
    }

    private void initCombos() {
        if (cmb_country.getItemCount() == 1) {
            for (Country.COUNTRY c : Country.COUNTRY.values()) {
                cmb_country.addItem(c.toString());
            }
        }
        cmb_city.setSelectedIndex(0);
        cmb_observatory.setSelectedIndex(0);
        cmb_city.setEnabled(false);
        cmb_observatory.setEnabled(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btn_newLocation = new org.jdesktop.swingx.JXButton();
        btn_editSelectedLocation = new org.jdesktop.swingx.JXButton();
        btn_deleteSelectedLocation = new org.jdesktop.swingx.JXButton();
        centerPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        btn_locationDetails = new javax.swing.JToggleButton();
        centerBottomPanel = new org.jdesktop.swingx.JXPanel();
        jXPanel2 = new org.jdesktop.swingx.JXPanel();
        lbl_locationName = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jXPanelGPS = new org.jdesktop.swingx.JXPanel();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jXLabel2 = new org.jdesktop.swingx.JXLabel();
        jXLabel3 = new org.jdesktop.swingx.JXLabel();
        txt_latitude = new javax.swing.JFormattedTextField();
        txt_longitude = new javax.swing.JFormattedTextField();
        txt_height = new javax.swing.JFormattedTextField();
        btn_detectByIP = new javax.swing.JButton();
        jXPanelObservatory = new org.jdesktop.swingx.JXPanel();
        cmb_country1 = new javax.swing.JComboBox<>();
        cmb_city1 = new javax.swing.JComboBox<>();
        cmb_observatory1 = new javax.swing.JComboBox<>();
        jXPanelCity = new org.jdesktop.swingx.JXPanel();
        cmb_country = new javax.swing.JComboBox<>();
        cmb_city = new javax.swing.JComboBox<>();
        cmb_observatory = new javax.swing.JComboBox<>();
        JXPanelAddress = new org.jdesktop.swingx.JXPanel();
        jXButton1 = new org.jdesktop.swingx.JXButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_address = new org.jdesktop.swingx.JXTextArea();
        jXPanel3 = new org.jdesktop.swingx.JXPanel();
        btn_saveLocation = new javax.swing.JButton();
        jXLabel4 = new org.jdesktop.swingx.JXLabel();
        jXLabel5 = new org.jdesktop.swingx.JXLabel();
        jLabel4 = new javax.swing.JLabel();
        location_subpanel_photo = new org.jdesktop.swingx.JXPanel();
        jMapViewer1 = new org.openstreetmap.gui.jmapviewer.JMapViewer();

        setTitle("Location Manager");

        jToolBar1.setBackground(new java.awt.Color(204, 204, 204));
        jToolBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar1.setForeground(javax.swing.UIManager.getDefaults().getColor("ToolBar.background"));
        jToolBar1.setRollover(true);
        jToolBar1.setMaximumSize(new java.awt.Dimension(2400, 32));
        jToolBar1.setMinimumSize(new java.awt.Dimension(800, 32));
        jToolBar1.setPreferredSize(new java.awt.Dimension(1000, 50));

        btn_newLocation.setText("New Location");
        btn_newLocation.setFocusable(false);
        btn_newLocation.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_newLocation.setMaximumSize(new java.awt.Dimension(400, 32));
        btn_newLocation.setMinimumSize(new java.awt.Dimension(100, 32));
        btn_newLocation.setPreferredSize(new java.awt.Dimension(200, 32));
        btn_newLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newLocationActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_newLocation);

        btn_editSelectedLocation.setText("Edit Selected");
        btn_editSelectedLocation.setFocusable(false);
        btn_editSelectedLocation.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_editSelectedLocation.setMaximumSize(new java.awt.Dimension(400, 32));
        btn_editSelectedLocation.setMinimumSize(new java.awt.Dimension(100, 32));
        btn_editSelectedLocation.setPreferredSize(new java.awt.Dimension(200, 32));
        btn_editSelectedLocation.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_editSelectedLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editSelectedLocationActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_editSelectedLocation);

        btn_deleteSelectedLocation.setText("Delete Selected");
        btn_deleteSelectedLocation.setFocusable(false);
        btn_deleteSelectedLocation.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_deleteSelectedLocation.setMaximumSize(new java.awt.Dimension(400, 32));
        btn_deleteSelectedLocation.setMinimumSize(new java.awt.Dimension(100, 32));
        btn_deleteSelectedLocation.setPreferredSize(new java.awt.Dimension(200, 32));
        btn_deleteSelectedLocation.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_deleteSelectedLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteSelectedLocationActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_deleteSelectedLocation);

        table.setAutoCreateRowSorter(true);
        table.setFont(new java.awt.Font("Lucida Grande", 0, 13)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Location name", "Latitude", "Longitude", "Altitude", "Timezone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setColumnSelectionAllowed(true);
        table.setMaximumSize(new java.awt.Dimension(2147483647, 640));
        table.setMinimumSize(new java.awt.Dimension(110, 32));
        table.setRowHeight(25);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);
        table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(30);
            table.getColumnModel().getColumn(3).setPreferredWidth(25);
        }

        javax.swing.GroupLayout centerPanelLayout = new javax.swing.GroupLayout(centerPanel);
        centerPanel.setLayout(centerPanelLayout);
        centerPanelLayout.setHorizontalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(0, 0, 0))
        );
        centerPanelLayout.setVerticalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
        );

        btn_locationDetails.setText("Show Location Details");
        btn_locationDetails.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_locationDetails.setFocusable(false);
        btn_locationDetails.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_locationDetails.setMaximumSize(new java.awt.Dimension(300, 28));
        btn_locationDetails.setMinimumSize(new java.awt.Dimension(100, 28));
        btn_locationDetails.setPreferredSize(new java.awt.Dimension(200, 28));
        btn_locationDetails.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_locationDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_locationDetailsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jXPanel1Layout = new javax.swing.GroupLayout(jXPanel1);
        jXPanel1.setLayout(jXPanel1Layout);
        jXPanel1Layout.setHorizontalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_locationDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jXPanel1Layout.setVerticalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_locationDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jXPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_locationName.setBackground(javax.swing.UIManager.getDefaults().getColor("Nb.browser.picker.background.light"));
        lbl_locationName.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lbl_locationName.setForeground(new java.awt.Color(51, 51, 51));
        lbl_locationName.setText("New location");

        jXLabel1.setText("Latitude ");

        jXLabel2.setText("Longitude ");

        jXLabel3.setText("Altitude (m)");

        txt_latitude.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txt_latitude.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_latitude.setToolTipText("Please enter latitude in degrees (decimal value).");
        txt_latitude.setMaximumSize(new java.awt.Dimension(400, 2147483647));

        txt_longitude.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.######"))));
        txt_longitude.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_longitude.setToolTipText("Please enter latitude in degrees (decimal value).");
        txt_longitude.setMaximumSize(new java.awt.Dimension(400, 2147483647));

        txt_height.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.###"))));
        txt_height.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_height.setToolTipText("Please enter latitude in degrees (decimal value).");

        btn_detectByIP.setText("Detect by IP");
        btn_detectByIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_detectByIPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jXPanelGPSLayout = new javax.swing.GroupLayout(jXPanelGPS);
        jXPanelGPS.setLayout(jXPanelGPSLayout);
        jXPanelGPSLayout.setHorizontalGroup(
            jXPanelGPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanelGPSLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jXPanelGPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jXPanelGPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_detectByIP, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jXPanelGPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txt_height, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_latitude, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_longitude, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jXPanelGPSLayout.setVerticalGroup(
            jXPanelGPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanelGPSLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jXPanelGPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_latitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jXPanelGPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_longitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jXPanelGPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_height, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_detectByIP)
                .addContainerGap(225, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("GPS Coords", jXPanelGPS);

        jXPanelObservatory.setToolTipText("Start by picking a country, then choose either a City or an Observatory.");

        cmb_country1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select country" }));
        cmb_country1.setMaximumSize(new java.awt.Dimension(400, 32767));
        cmb_country1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_country1ItemStateChanged(evt);
            }
        });

        cmb_city1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select city" }));
        cmb_city1.setMaximumSize(new java.awt.Dimension(400, 32767));
        cmb_city1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_city1ItemStateChanged(evt);
            }
        });

        cmb_observatory1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select observatory" }));
        cmb_observatory1.setToolTipText("Start by selecting a country, then pick either  a City or an Observatory to get approximate GPS coordinates.");
        cmb_observatory1.setMaximumSize(new java.awt.Dimension(400, 32767));
        cmb_observatory1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_observatory1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jXPanelObservatoryLayout = new javax.swing.GroupLayout(jXPanelObservatory);
        jXPanelObservatory.setLayout(jXPanelObservatoryLayout);
        jXPanelObservatoryLayout.setHorizontalGroup(
            jXPanelObservatoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanelObservatoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXPanelObservatoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmb_country1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmb_city1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmb_observatory1, 0, 401, Short.MAX_VALUE))
                .addContainerGap())
        );
        jXPanelObservatoryLayout.setVerticalGroup(
            jXPanelObservatoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanelObservatoryLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(cmb_country1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmb_city1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmb_observatory1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Observatory", jXPanelObservatory);

        jXPanelCity.setToolTipText("Start by picking a country, then choose either a City or an Observatory.");

        cmb_country.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select country" }));
        cmb_country.setMaximumSize(new java.awt.Dimension(400, 32767));
        cmb_country.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_countryItemStateChanged(evt);
            }
        });

        cmb_city.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select city" }));
        cmb_city.setMaximumSize(new java.awt.Dimension(400, 32767));
        cmb_city.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_cityItemStateChanged(evt);
            }
        });

        cmb_observatory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select observatory" }));
        cmb_observatory.setToolTipText("Start by selecting a country, then pick either  a City or an Observatory to get approximate GPS coordinates.");
        cmb_observatory.setMaximumSize(new java.awt.Dimension(400, 32767));
        cmb_observatory.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_observatoryItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jXPanelCityLayout = new javax.swing.GroupLayout(jXPanelCity);
        jXPanelCity.setLayout(jXPanelCityLayout);
        jXPanelCityLayout.setHorizontalGroup(
            jXPanelCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanelCityLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXPanelCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmb_country, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmb_city, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmb_observatory, 0, 401, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jXPanelCityLayout.setVerticalGroup(
            jXPanelCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanelCityLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(cmb_country, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmb_city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmb_observatory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("City", jXPanelCity);
        jXPanelCity.getAccessibleContext().setAccessibleName("Pick a City or an Observatory");

        jXButton1.setText("Geolocate");
        jXButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXButton1ActionPerformed(evt);
            }
        });

        txt_address.setColumns(20);
        txt_address.setRows(5);
        jScrollPane2.setViewportView(txt_address);

        javax.swing.GroupLayout JXPanelAddressLayout = new javax.swing.GroupLayout(JXPanelAddress);
        JXPanelAddress.setLayout(JXPanelAddressLayout);
        JXPanelAddressLayout.setHorizontalGroup(
            JXPanelAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JXPanelAddressLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JXPanelAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JXPanelAddressLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jXButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        JXPanelAddressLayout.setVerticalGroup(
            JXPanelAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JXPanelAddressLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Address", JXPanelAddress);

        btn_saveLocation.setText("Save Location");
        btn_saveLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveLocationActionPerformed(evt);
            }
        });

        jXLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jXLabel4.setText("Selected coords: ");

        jXLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jXLabel5.setText("0.2323213123 - 131312313123 (214)");

        javax.swing.GroupLayout jXPanel3Layout = new javax.swing.GroupLayout(jXPanel3);
        jXPanel3.setLayout(jXPanel3Layout);
        jXPanel3Layout.setHorizontalGroup(
            jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 26, Short.MAX_VALUE)
                .addComponent(btn_saveLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jXPanel3Layout.setVerticalGroup(
            jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btn_saveLocation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
            .addGroup(jXPanel3Layout.createSequentialGroup()
                .addComponent(jXLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jXLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        jLabel4.setText("* GPS coordinates will usually provide results that are more precise.");

        location_subpanel_photo.setBackground(new java.awt.Color(204, 204, 204));
        location_subpanel_photo.setBorder(new javax.swing.border.MatteBorder(null));
        location_subpanel_photo.setMaximumSize(new java.awt.Dimension(600, 32767));
        location_subpanel_photo.setPreferredSize(new java.awt.Dimension(334, 100));

        javax.swing.GroupLayout location_subpanel_photoLayout = new javax.swing.GroupLayout(location_subpanel_photo);
        location_subpanel_photo.setLayout(location_subpanel_photoLayout);
        location_subpanel_photoLayout.setHorizontalGroup(
            location_subpanel_photoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, location_subpanel_photoLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jMapViewer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        location_subpanel_photoLayout.setVerticalGroup(
            location_subpanel_photoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jMapViewer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jXPanel2Layout = new javax.swing.GroupLayout(jXPanel2);
        jXPanel2.setLayout(jXPanel2Layout);
        jXPanel2Layout.setHorizontalGroup(
            jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jXPanel2Layout.createSequentialGroup()
                        .addGroup(jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTabbedPane2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jXPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(location_subpanel_photo, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)))
                    .addComponent(lbl_locationName, javax.swing.GroupLayout.PREFERRED_SIZE, 819, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jXPanel2Layout.setVerticalGroup(
            jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_locationName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane2)
                    .addComponent(location_subpanel_photo, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
                .addGroup(jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jXPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jXPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout centerBottomPanelLayout = new javax.swing.GroupLayout(centerBottomPanel);
        centerBottomPanel.setLayout(centerBottomPanelLayout);
        centerBottomPanelLayout.setHorizontalGroup(
            centerBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerBottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        centerBottomPanelLayout.setVerticalGroup(
            centerBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerBottomPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jXPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(centerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jXPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(centerBottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(centerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jXPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(centerBottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_locationDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_locationDetailsActionPerformed
        String btn_text;
        if (this.btn_locationDetails.isSelected()) {
            btn_text = "Hide Location Details";
        } else {
            btn_text = "Show Location Details";
        }

        this.centerBottomPanel.setVisible(this.btn_locationDetails.isSelected());
        this.btn_locationDetails.setText(btn_text);
    }//GEN-LAST:event_btn_locationDetailsActionPerformed

    private void btn_newLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newLocationActionPerformed
        this.centerBottomPanel.setVisible((true));
        this.btn_locationDetails.setSelected(true);
        this.btn_locationDetails.setText("Hide Location Details");
        this.newLocation = null;

        if (curLocationBeingEdited > 0) {
            String msg = "You were already editing an existing location. \n"
                    + "Do you prefer to keep editing or to start over \n"
                    + "with an empty form?";

            String[] options = {"Keep Editing", "Start Over"};
            int option = JOptionPane.showOptionDialog(this, msg, "Question",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if (option == 1) {
                clearBottomPanel();
                curLocationBeingEdited = -1;
            }

        } else if (!isBotomPanelEmpty()) {
            String msg = "You were already editing a new location. \n"
                    + "Do you prefer to keep editing or to start over \n"
                    + "with an empty form?";

            String[] options = {"Keep Editing", "Start Over"};
            int option = JOptionPane.showOptionDialog(this, msg, "Question",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if (option == 1) {
                clearBottomPanel();
                curLocationBeingEdited = -1;
            }
        }

    }//GEN-LAST:event_btn_newLocationActionPerformed

    private void btn_editSelectedLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editSelectedLocationActionPerformed
        if (getCurrentSelectedLocationID() == -1) {
            String msg = "You must select a location in the table to be able\n"
                    + "to edit it. If you just want to create a new location,\n"
                    + "please use the New Location button.";
            JOptionPane.showMessageDialog(this, msg, "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        this.centerBottomPanel.setVisible((true));
        this.btn_locationDetails.setSelected(true);
        this.btn_locationDetails.setText("Hide Location Details");

        if (curLocationBeingEdited > 0) {
            String msg = "You were already editing an existing location. \n"
                    + "Do you prefer to keep editing the previous location \n"
                    + "or to start editing this one?";

            String[] options = {"Keep Editing Previous", "Edit This One"};
            int option = JOptionPane.showOptionDialog(this, msg, "Question",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if (option == 1) {
                curLocationBeingEdited = getCurrentSelectedLocationID();
                loadBottomPanel(curLocationBeingEdited);
            }

        } else if (!isBotomPanelEmpty()) {
            String msg = "You were already editing a new location. \n"
                    + "Do you prefer to keep editing or to start editing \n"
                    + "this location?";

            String[] options = {"Keep Editing Previous", "Edit This One"};
            int option = JOptionPane.showOptionDialog(this, msg, "Question",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if (option == 1) {
                curLocationBeingEdited = getCurrentSelectedLocationID();
                loadBottomPanel(curLocationBeingEdited);
            }
        } else {
            curLocationBeingEdited = getCurrentSelectedLocationID();
            loadBottomPanel(curLocationBeingEdited);
        }
    }//GEN-LAST:event_btn_editSelectedLocationActionPerformed

    private void btn_deleteSelectedLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteSelectedLocationActionPerformed
        int id = getCurrentSelectedLocationID();
        if (id == -1) {
            String msg = "You must select a location in the table to be able\n"
                    + "to delete it.";
            JOptionPane.showMessageDialog(this, msg, "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            mydb.deleteLocation(id);
            updateTable();
        }
    }//GEN-LAST:event_btn_deleteSelectedLocationActionPerformed

    private void btn_saveLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveLocationActionPerformed
        Integer locId;
        String locName = "";
        Double locTz;
        Double latitude_rad, longitude_rad;
        int height;
        String msg;

        // Verify if there are valid GPS coordinates in their fields.
        if (txt_latitude.getText().isEmpty()) {
            msg = "Please specifiy latitude as a number, in degrees.";
            JOptionPane.showMessageDialog(this, msg, "Missing parameter",
                JOptionPane.WARNING_MESSAGE);
            txt_latitude.requestFocusInWindow();
            return;
        } else {
            latitude_rad = Constant.DEG_TO_RAD * Double.parseDouble(
                txt_latitude.getText().replace(',', '.'));
        }

        if (txt_longitude.getText().isEmpty()) {
            msg = "Please specifiy longitude as a number, in degrees.";
            JOptionPane.showMessageDialog(this, msg, "Missing parameter",
                JOptionPane.WARNING_MESSAGE);
            txt_longitude.requestFocusInWindow();
            return;
        } else {
            longitude_rad = Constant.DEG_TO_RAD * Double.parseDouble(
                txt_longitude.getText().replace(',', '.'));
        }

        if (txt_height.getText().isEmpty()) {
            msg = "Please enter an integer number (height in meters "
            + "above the sea level) in the Altitude field.";
            JOptionPane.showMessageDialog(this, msg, "Missing parameter",
                JOptionPane.WARNING_MESSAGE);
            txt_height.requestFocusInWindow();
            return;
        } else {
            height = Integer.parseInt(txt_height.getText());
        }

        locId = this.curLocationBeingEdited;

        if (locId == -1) {
            msg = "Please enter a new name for this observatory: ";
            do {
                if (locName.length() > 127) {
                    msg = "The name should not exceed 128 characters. \n"
                    + "Which, by the way, should be more that enough. ;-)";
                    JOptionPane.showMessageDialog(this, msg, "Incorrect parameter",
                        JOptionPane.WARNING_MESSAGE);
                }
                locName = (String) JOptionPane.showInputDialog(null, msg, "Preparing to save Location",
                    JOptionPane.QUESTION_MESSAGE, null, null, locName);

            } while (locName.trim().isEmpty() || locName.trim().length() > 128);
            locName = locName.trim();
        } else {
            locName = lbl_locationName.getText();
        }

        locTz = Location.getTimeZoneOffset(latitude_rad, longitude_rad);

        //        System.out.println("=== BTN_SAVE_LOCATION =========================");
        //        System.out.println("LAT_RAD: " + latitude_rad);
        //        System.out.println("LAT_DEG: " + latitude_rad * Constant.RAD_TO_DEG);
        //        System.out.println("LON_RAD: " + longitude_rad);
        //        System.out.println("LON_DEG: " + longitude_rad * Constant.RAD_TO_DEG);
        //        System.out.println("LOC_TZ:  " + locTz);
        //        System.out.println("== /BTN_SAVE_LOCATION =========================");
        // Save to the database
        int status = mydb.insertOrUpdateLocation(locId, locName, latitude_rad,
            longitude_rad, height, txt_address.getText(), locTz);

        // Update the table
        // Present an option to create a new session using this location.
        // Hide the bottom panel
        this.clearBottomPanel();
        this.curLocationBeingEdited = -1;
        this.btn_locationDetails.setSelected(false);
        this.centerBottomPanel.setVisible(false);
        this.btn_locationDetails.setText("Show Location Details");
        this.updateTable();
    }//GEN-LAST:event_btn_saveLocationActionPerformed

    private void jXButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jXButton1ActionPerformed

    private void cmb_observatoryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_observatoryItemStateChanged
        // Not forgetting that we have a "Select city" item in the combo box.
        if (cmb_observatory.getSelectedIndex() < 1) {
            return;
        }
        String selectedObservatory = cmb_observatory.getSelectedItem().toString();

        ObservatoryElement observatory;
        try {

            // TODO:
            // We definitely should add a clear way to choose between multiple
            // cities in case they happen to have the same name, but for now,
            // for the sake of a faster MVP release, let's just present the user
            // with a single, quick result…
            if (cmb_country.getSelectedIndex() >= 1) {
                // Not forgetting "Select country" is item #0 in the combo box.
                String selectedCountry = cmb_country.getSelectedItem().toString();
                COUNTRY countryID = Country.getID(selectedCountry);
                observatory = Observatory.findObservatorybyName(selectedObservatory, countryID);
            } else {
                observatory = Observatory.findObservatorybyName(selectedObservatory);
            }

            Double lat = observatory.latitude;
            Double lon = observatory.longitude;
            int height = observatory.height;

            DecimalFormat df = new DecimalFormat("###0.00000");
            txt_latitude.setText(df.format(lat));
            txt_longitude.setText(df.format(lon));
            // TODO: See if we can provide a better default value here.
            txt_height.setText(Integer.toString(height));

        } catch (JPARSECException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cmb_observatoryItemStateChanged

    private void cmb_cityItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_cityItemStateChanged
        // Not forgetting that we have a "Select city" item in the combo box.
        if (cmb_city.getSelectedIndex() < 1) {
            return;
        }
        CityElement realCity;
        CityElement[] cities;

        String selectedCity = cmb_city.getSelectedItem().toString();

        try {
            String selectedCountry = cmb_country.getSelectedItem().toString();
            COUNTRY countryID = Country.getID(selectedCountry);
            cities = City.getCities(countryID);

            realCity = cities[0];
            // Make sure the city is at least in the same country...
            for (CityElement city : cities) {
                if (city.name.equalsIgnoreCase(selectedCity)) {
                    realCity = city;
                }
            }

            Double lat_deg = realCity.latitude;
            Double lon_deg = realCity.longitude;
            int height = realCity.height;
            String lat = lat_deg.toString();
            lat = lat.substring(0, Math.min(lat.length(), 8));

            String lon = lon_deg.toString();
            lon = lon.substring(0, Math.min(lon.length(), 8));

            txt_latitude.setText(lat);
            txt_longitude.setText(lon);
            txt_height.setText(Integer.toString(height));

        } catch (JPARSECException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cmb_cityItemStateChanged

    private void cmb_countryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_countryItemStateChanged
        COUNTRY countryID;
        String selectedCountry;

        if (cmb_country.getSelectedIndex() < 1) {
            initCombos();
            return;
        }

        selectedCountry = cmb_country.getSelectedItem().toString();

        try {
            countryID = Country.getID(selectedCountry);
        } catch (JPARSECException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        // Fill cities combo, filtering by country
        try {
            ArrayList<String> cities_names = new ArrayList<>();
            CityElement[] cities = City.getCities(countryID);
            for (CityElement c : cities) {
                cities_names.add(c.name);
            }
            Collections.sort(cities_names);
            cmb_city.removeAllItems();
            cmb_city.addItem("Select city");
            for (String city : cities_names) {
                cmb_city.addItem(city);
            }
            cmb_city.setEnabled(true);

        } catch (JPARSECException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            cmb_city.removeAllItems();
            cmb_city.addItem("[No know cities]");
            cmb_city.setEnabled(false);
        }

        // Fill observatories combo, filtering by country
        try {
            ArrayList<String> observatories_names = new ArrayList<>();
            ObservatoryElement[] observatories = Observatory.getObservatoriesByCountry(countryID);
            for (ObservatoryElement o : observatories) {
                if (!o.name.trim().isEmpty()) {
                    observatories_names.add(o.name);
                }
            }
            Collections.sort(observatories_names);
            cmb_observatory.removeAllItems();
            cmb_observatory.addItem("Select observatory");
            for (String observatory : observatories_names) {
                cmb_observatory.addItem(observatory);
            }
            cmb_observatory.setEnabled(true);

        } catch (JPARSECException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            cmb_observatory.removeAllItems();
            cmb_observatory.addItem("[No know observatories]");
            cmb_observatory.setEnabled(false);
        }
    }//GEN-LAST:event_cmb_countryItemStateChanged

    private void cmb_observatory1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_observatory1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_observatory1ItemStateChanged

    private void cmb_city1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_city1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_city1ItemStateChanged

    private void cmb_country1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_country1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_country1ItemStateChanged

    private void btn_detectByIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detectByIPActionPerformed
        String ipErrorMsg = "We were not able to get GPS coordinates from your\n"
        + "current IP address. Please select enter your coordinates\n"
        + "manually or choose country/city.\n\n";

        try {
            // Get current GPS coordinates from current outbound IP address
            newLocation = new Location();
            Double latitude_deg = newLocation.getLatitudeRad() * Constant.RAD_TO_DEG;
            Double longitude_deg = newLocation.getLongitudeRad() * Constant.RAD_TO_DEG;

            txt_latitude.setText(latitude_deg.toString());
            txt_longitude.setText(longitude_deg.toString());
            txt_height.setText(Constants.NTPConstants.DEFAULT_LOCATION_HEIGHT);
            String msg = "According to IP-API.com, you're near " + newLocation.getName()
            + ".\nPlease keep in mind that GPS coordinates obtained by \n"
            + "this process are just (inherently imprecise) estimations.\n\n"
            + "Currently, altitude cannot be determined automatically,\n"
            + "so we have preset it for you to the median elevation\n"
            + "inhabited by humans. Please change it if necessary.";
            JOptionPane.showMessageDialog(this, msg, "Warning",
                JOptionPane.WARNING_MESSAGE);

        } catch (Exception ex) {
            ipErrorMsg += ex.getMessage().replace(": ", "\n");
            JOptionPane.showMessageDialog(this, ipErrorMsg, "Missing parameter",
                JOptionPane.WARNING_MESSAGE);
            txt_longitude.requestFocusInWindow();
        }
    }//GEN-LAST:event_btn_detectByIPActionPerformed

    public boolean isBotomPanelEmpty() {
        return cmb_country.getSelectedIndex() < 1
                && cmb_city.getSelectedIndex() < 1
                && txt_latitude.getText().isEmpty()
                && txt_longitude.getText().isEmpty()
                && txt_height.getText().isEmpty()
                && txt_address.getText().trim().isEmpty();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LocationManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LocationManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXPanel JXPanelAddress;
    private org.jdesktop.swingx.JXButton btn_deleteSelectedLocation;
    private javax.swing.JButton btn_detectByIP;
    private org.jdesktop.swingx.JXButton btn_editSelectedLocation;
    private javax.swing.JToggleButton btn_locationDetails;
    private org.jdesktop.swingx.JXButton btn_newLocation;
    private javax.swing.JButton btn_saveLocation;
    private org.jdesktop.swingx.JXPanel centerBottomPanel;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JComboBox<String> cmb_city;
    private javax.swing.JComboBox<String> cmb_city1;
    private javax.swing.JComboBox<String> cmb_country;
    private javax.swing.JComboBox<String> cmb_country1;
    private javax.swing.JComboBox<String> cmb_observatory;
    private javax.swing.JComboBox<String> cmb_observatory1;
    private javax.swing.JLabel jLabel4;
    private org.openstreetmap.gui.jmapviewer.JMapViewer jMapViewer1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JToolBar jToolBar1;
    private org.jdesktop.swingx.JXButton jXButton1;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXLabel jXLabel2;
    private org.jdesktop.swingx.JXLabel jXLabel3;
    private org.jdesktop.swingx.JXLabel jXLabel4;
    private org.jdesktop.swingx.JXLabel jXLabel5;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private org.jdesktop.swingx.JXPanel jXPanel2;
    private org.jdesktop.swingx.JXPanel jXPanel3;
    private org.jdesktop.swingx.JXPanel jXPanelCity;
    private org.jdesktop.swingx.JXPanel jXPanelGPS;
    private org.jdesktop.swingx.JXPanel jXPanelObservatory;
    private javax.swing.JLabel lbl_locationName;
    private javax.swing.JLabel lbl_photo;
    private javax.swing.JLabel lbl_photo_angDiameter;
    private javax.swing.JLabel lbl_photo_declination;
    private javax.swing.JLabel lbl_photo_mag;
    private javax.swing.JLabel lbl_photo_ra;
    private org.jdesktop.swingx.JXPanel location_subpanel_photo;
    private org.jdesktop.swingx.JXPanel subpanel_photo;
    private javax.swing.JTable table;
    private org.jdesktop.swingx.JXTextArea txt_address;
    private javax.swing.JFormattedTextField txt_height;
    private javax.swing.JFormattedTextField txt_latitude;
    private javax.swing.JFormattedTextField txt_longitude;
    // End of variables declaration//GEN-END:variables

    private void clearBottomPanel() {
        lbl_locationName.setText("New Location");
        cmb_country.setSelectedIndex(0);
        cmb_city.setSelectedIndex(0);
        txt_latitude.setText("");
        txt_longitude.setText("");
        txt_height.setText("");
        txt_address.setText("");
        this.newLocation = null;
    }

    /**
     * Returns the location ID for the current selected row in the table. In
     * case there none selected, it shoud return -1;
     *
     * @return
     */
    private int getCurrentSelectedLocationID() {
        try {
            int sel_row = table.getSelectedRows()[0];
            return Integer.parseInt(table.getModel()
                    .getValueAt(sel_row, 0)
                    .toString());
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            return -1;
        }
    }

    private void loadBottomPanel(int id) {
        clearBottomPanel();
        //txt_address 
        this.newLocation = mydb.getOneLocation(id);
        lbl_locationName.setText(newLocation.getName());

        Double lat_deg = newLocation.getLatitudeRad() * Constant.RAD_TO_DEG;
        String lat = lat_deg.toString();
        lat = lat.substring(0, Math.min(lat.length(), 8));

        Double lon_deg = newLocation.getLongitudeRad() * Constant.RAD_TO_DEG;
        String lon = lon_deg.toString();
        lon = lon.substring(0, Math.min(lon.length(), 8));

        txt_latitude.setText(lat);
        txt_longitude.setText(lon);
        txt_height.setText(Integer.toString(newLocation.getHeight()));
    }
}

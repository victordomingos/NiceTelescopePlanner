/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import jparsec.math.Constant;
import jparsec.observer.City;
import jparsec.observer.CityElement;
import jparsec.observer.Country;
import jparsec.observer.Country.COUNTRY;
import jparsec.observer.LocationElement;
import jparsec.util.JPARSECException;

import javax.swing.JOptionPane;

import core.Location;
import core.OnlineLocation;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import jparsec.observer.Observatory;
import jparsec.observer.ObservatoryElement;

/**
 *
 * @author victordomingos
 */
public class LocationManager extends javax.swing.JFrame {

    private final db.DbConnection mydb = new db.DbConnection();
    // -1 means form empty or editing a new session; 
    // Any other number means the db ID of an existing location being edited/shown.
    private int curLocationBeingEdited = -1;

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

        this.subpanel_enterAddress.setVisible(false); // Rescheduled to a future release
        this.img_map.setVisible(false);               // Rescheduled to a future release

        this.addComponentListener(new ComponentAdapter() {
//            public void componentHidden(ComponentEvent e) {
//                /* code run when component hidden*/
//            }

            public void componentShown(ComponentEvent e) {
                updateTable();
            }
        });

        initCombos();
        

    }

    private void updateTable() {
        Double lat_rad, lat_deg, lon_rad, lon_deg;
        String lat, lon, height;

        // get all locations from the database
        ArrayList<Location> allLocations = mydb.getAllLocations();

        //Set columns headers and table Model - make it non-editable
        String columns[] = {"ID", "Location name", "Latitude", "Longitude", "Altitude"};
        
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

            lat_rad = allLocations.get(i).getLatitude();
            lat_deg = lat_rad * Constant.RAD_TO_DEG;
            lat = String.format("%.6f", lat_deg);
            lat = lat.substring(0, Math.min(lat.length(), 8));

            lon_rad = allLocations.get(i).getLongitude();
            lon_deg = lon_rad * Constant.RAD_TO_DEG;
            lon = String.format("%.6f", lon_deg);
            lon = lon.substring(0, Math.min(lon.length(), 8));

            height = Integer.toString(allLocations.get(i).getHeight());

            Object[] data = {id, name, lat, lon, height};
            LocationsTableModel.addRow(data);
        }

        // Set basic table formatting
        table.getColumn("ID").setMaxWidth(50);
        table.getColumn("Latitude").setMaxWidth(160);
        table.getColumn("Longitude").setMaxWidth(160);
        table.getColumn("Altitude").setMaxWidth(80);
        table.getColumn("ID").setPreferredWidth(50);
        table.getColumn("Latitude").setPreferredWidth(160);
        table.getColumn("Longitude").setPreferredWidth(160);
        table.getColumn("Altitude").setPreferredWidth(80);
    }

    private void initCombos() {
        if(cmb_country.getItemCount() == 1)
        {
            for (Country.COUNTRY c : Country.COUNTRY.values()) {
                cmb_country.addItem(c.toString());
            }
        }
        cmb_city.setSelectedIndex(0);
        cmb_observatory.setSelectedIndex(0);
        cmb_city.setEnabled(false);
        cmb_observatory.setEnabled(false);
//
//        try {
//            CityElement[] cities = City.getAllCities();
//            ArrayList<String> cities_names = new ArrayList<>();
//
//            for (CityElement c : cities) {
//                cities_names.add(c.name);
//            }
//
//            Collections.sort(cities_names);
//
//            for (String city : cities_names) {
//                cmb_city.addItem(city);
//            }
//            
//            
//            ObservatoryElement[] observatories = Observatory.getAllObservatories();
//            ArrayList<String> observatories_names = new ArrayList<>();
//
//            for (ObservatoryElement o : observatories) {
//                observatories_names.add(o.name);
//            }
//
//            Collections.sort(observatories_names);
//
//            for (String observatory : observatories_names) {
//                if(!observatory.trim().isEmpty()) {
//                    cmb_observatory.addItem(observatory);
//                }
//            }
//            
//            
//        } catch (JPARSECException e) {
//            System.out.println(e);
//        }

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
        jXPanel3 = new org.jdesktop.swingx.JXPanel();
        cmb_country = new javax.swing.JComboBox<>();
        cmb_city = new javax.swing.JComboBox<>();
        cmb_observatory = new javax.swing.JComboBox<>();
        jXPanel4 = new org.jdesktop.swingx.JXPanel();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jXLabel2 = new org.jdesktop.swingx.JXLabel();
        jXLabel3 = new org.jdesktop.swingx.JXLabel();
        txt_latitude = new javax.swing.JFormattedTextField();
        txt_longitude = new javax.swing.JFormattedTextField();
        txt_height = new javax.swing.JFormattedTextField();
        subpanel_enterAddress = new org.jdesktop.swingx.JXPanel();
        txt_address = new javax.swing.JTextField();
        jXPanel6 = new org.jdesktop.swingx.JXPanel();
        btn_saveLocation = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btn_detectByIP = new javax.swing.JButton();
        img_map = new org.jdesktop.swingx.JXImageView();
        lbl_locationName = new javax.swing.JLabel();

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
                "Location name", "Latitude", "Longitude", "Altitude"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
            .addComponent(jScrollPane1)
        );
        centerPanelLayout.setVerticalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addGap(0, 0, 0))
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
                .addContainerGap())
        );

        jXPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jXPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Pick City or Observatory"));
        jXPanel3.setToolTipText("Start by picking a country, then choose either a City or an Observatory.");

        cmb_country.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select country" }));
        cmb_country.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_countryItemStateChanged(evt);
            }
        });

        cmb_city.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select city" }));
        cmb_city.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_cityItemStateChanged(evt);
            }
        });

        cmb_observatory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select observatory" }));
        cmb_observatory.setToolTipText("Start by selecting a country, then pick either  a City or an Observatory to get approximate GPS coordinates.");
        cmb_observatory.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_observatoryItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jXPanel3Layout = new javax.swing.GroupLayout(jXPanel3);
        jXPanel3.setLayout(jXPanel3Layout);
        jXPanel3Layout.setHorizontalGroup(
            jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmb_city, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_country, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_observatory, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jXPanel3Layout.setVerticalGroup(
            jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmb_country, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(cmb_city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_observatory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jXPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Enter GPS coords"));

        jXLabel1.setText("Latitude ");

        jXLabel2.setText("Longitude ");

        jXLabel3.setText("Altitude (m)");

        txt_latitude.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txt_latitude.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_latitude.setToolTipText("Please enter latitude in degrees (decimal value).");

        txt_longitude.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.######"))));
        txt_longitude.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_longitude.setToolTipText("Please enter latitude in degrees (decimal value).");

        txt_height.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.###"))));
        txt_height.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_height.setToolTipText("Please enter latitude in degrees (decimal value).");

        javax.swing.GroupLayout jXPanel4Layout = new javax.swing.GroupLayout(jXPanel4);
        jXPanel4.setLayout(jXPanel4Layout);
        jXPanel4Layout.setHorizontalGroup(
            jXPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jXPanel4Layout.createSequentialGroup()
                        .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_latitude, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jXPanel4Layout.createSequentialGroup()
                        .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_longitude, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jXPanel4Layout.createSequentialGroup()
                        .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(txt_height, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jXPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txt_height, txt_latitude, txt_longitude});

        jXPanel4Layout.setVerticalGroup(
            jXPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel4Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(jXPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_latitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jXPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_longitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jXPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_height, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        subpanel_enterAddress.setBorder(javax.swing.BorderFactory.createTitledBorder("Enter an address"));

        javax.swing.GroupLayout subpanel_enterAddressLayout = new javax.swing.GroupLayout(subpanel_enterAddress);
        subpanel_enterAddress.setLayout(subpanel_enterAddressLayout);
        subpanel_enterAddressLayout.setHorizontalGroup(
            subpanel_enterAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subpanel_enterAddressLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_address, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );
        subpanel_enterAddressLayout.setVerticalGroup(
            subpanel_enterAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subpanel_enterAddressLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        btn_saveLocation.setText("Save Location");
        btn_saveLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveLocationActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        jLabel3.setText("Choose one of these methods (GPS coordinates will provide results that are more precise).");

        btn_detectByIP.setText("Detect by IP");
        btn_detectByIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_detectByIPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jXPanel6Layout = new javax.swing.GroupLayout(jXPanel6);
        jXPanel6.setLayout(jXPanel6Layout);
        jXPanel6Layout.setHorizontalGroup(
            jXPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_detectByIP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_saveLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jXPanel6Layout.setVerticalGroup(
            jXPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel6Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addGroup(jXPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_saveLocation)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_detectByIP)))
        );

        javax.swing.GroupLayout img_mapLayout = new javax.swing.GroupLayout(img_map);
        img_map.setLayout(img_mapLayout);
        img_mapLayout.setHorizontalGroup(
            img_mapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        img_mapLayout.setVerticalGroup(
            img_mapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        lbl_locationName.setBackground(javax.swing.UIManager.getDefaults().getColor("Nb.browser.picker.background.light"));
        lbl_locationName.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lbl_locationName.setForeground(new java.awt.Color(51, 51, 51));
        lbl_locationName.setText("New location");

        javax.swing.GroupLayout jXPanel2Layout = new javax.swing.GroupLayout(jXPanel2);
        jXPanel2.setLayout(jXPanel2Layout);
        jXPanel2Layout.setHorizontalGroup(
            jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_locationName, javax.swing.GroupLayout.PREFERRED_SIZE, 819, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jXPanel2Layout.createSequentialGroup()
                        .addComponent(jXPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jXPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(subpanel_enterAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(img_map, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jXPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jXPanel3, jXPanel4});

        jXPanel2Layout.setVerticalGroup(
            jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_locationName, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(subpanel_enterAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jXPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jXPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(img_map, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jXPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jXPanel3, jXPanel4, subpanel_enterAddress});

        jXPanel3.getAccessibleContext().setAccessibleName("Pick a City or an Observatory");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerBottomPanelLayout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jXPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(centerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jXPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(centerBottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        if (this.btn_locationDetails.isSelected()){
            btn_text = "Hide Location Details";
        }
        else {
            btn_text = "Show Location Details";            
        }
        
        this.centerBottomPanel.setVisible(this.btn_locationDetails.isSelected());
        this.btn_locationDetails.setText(btn_text);
    }//GEN-LAST:event_btn_locationDetailsActionPerformed

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
        } 
        catch (NullPointerException e)
        {   
            cmb_city.removeAllItems();
            cmb_city.addItem("[No know cities]");
            cmb_city.setEnabled(false);
        }
        
        
        // Fill observatories combo, filtering by country
        try {
            ArrayList<String> observatories_names = new ArrayList<>();
            ObservatoryElement[] observatories = Observatory.getObservatoriesByCountry(countryID);
            for (ObservatoryElement o : observatories) {
                if(!o.name.trim().isEmpty()){
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
        }
        catch (NullPointerException e)
        {   
            cmb_observatory.removeAllItems();
            cmb_observatory.addItem("[No know observatories]");
            cmb_observatory.setEnabled(false);
        }
    }//GEN-LAST:event_cmb_countryItemStateChanged

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

    private void btn_saveLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveLocationActionPerformed
        Integer locId;
        String locName = "";
        Double locTz = 0.0;
        Double latitude, longitude;
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
            latitude = Constant.DEG_TO_RAD * Double.parseDouble(
                    txt_latitude.getText().replace(',', '.'));
        }

        if (txt_longitude.getText().isEmpty()) {
            msg = "Please specifiy longitude as a number, in degrees.";
            JOptionPane.showMessageDialog(this, msg, "Missing parameter",
                    JOptionPane.WARNING_MESSAGE);
            txt_longitude.requestFocusInWindow();
            return;
        } else {
            longitude = Constant.DEG_TO_RAD * Double.parseDouble(
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

        if(locId == -1) {
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
        }
        else {
            locName = lbl_locationName.getText();
        }
        
        // Save to the database
        int status = mydb.insertOrUpdateLocation(locId, locName, latitude,
                longitude, height, txt_address.getText(), locTz);

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

    private void btn_newLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newLocationActionPerformed
        this.centerBottomPanel.setVisible((true));
        this.btn_locationDetails.setSelected(true);
        this.btn_locationDetails.setText("Hide Location Details");

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

    private void btn_detectByIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detectByIPActionPerformed
        String ipErrorMsg = "We were not able to get GPS coordinates from your\n"
                + "current IP address. Please select enter your coordinates\n"
                + "manually or choose country/city.\n\n";

        try {
            // Get current GPS coordinates from current outbound IP address
            Location onloc = new Location();
            txt_latitude.setText(onloc.getLatitude().toString());
            txt_longitude.setText(onloc.getLongitude().toString());
            txt_height.setText(Constants.Constants.DEFAULT_LOCATION_HEIGHT);
            String msg = "According to IP-API.com, you're near " + onloc.getName()
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
            }
            else{
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LocationManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LocationManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LocationManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LocationManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LocationManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXButton btn_deleteSelectedLocation;
    private javax.swing.JButton btn_detectByIP;
    private org.jdesktop.swingx.JXButton btn_editSelectedLocation;
    private javax.swing.JToggleButton btn_locationDetails;
    private org.jdesktop.swingx.JXButton btn_newLocation;
    private javax.swing.JButton btn_saveLocation;
    private org.jdesktop.swingx.JXPanel centerBottomPanel;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JComboBox<String> cmb_city;
    private javax.swing.JComboBox<String> cmb_country;
    private javax.swing.JComboBox<String> cmb_observatory;
    private org.jdesktop.swingx.JXImageView img_map;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXLabel jXLabel2;
    private org.jdesktop.swingx.JXLabel jXLabel3;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private org.jdesktop.swingx.JXPanel jXPanel2;
    private org.jdesktop.swingx.JXPanel jXPanel3;
    private org.jdesktop.swingx.JXPanel jXPanel4;
    private org.jdesktop.swingx.JXPanel jXPanel6;
    private javax.swing.JLabel lbl_locationName;
    private org.jdesktop.swingx.JXPanel subpanel_enterAddress;
    private javax.swing.JTable table;
    private javax.swing.JTextField txt_address;
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
        Location loc = mydb.getOneLocation(id);
        lbl_locationName.setText(loc.getName());

        Double lat_deg = loc.getLatitude() * Constant.RAD_TO_DEG;
        String lat = lat_deg.toString();
        lat = lat.substring(0, Math.min(lat.length(), 8));

        Double lon_deg = loc.getLongitude() * Constant.RAD_TO_DEG;
        String lon = lon_deg.toString();
        lon = lon.substring(0, Math.min(lon.length(), 8));
        
        txt_latitude.setText(lat);
        txt_longitude.setText(lon);
        txt_height.setText(Integer.toString(loc.getHeight()));
    }
}

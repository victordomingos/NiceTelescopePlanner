/*
 * Copyright (C) 2019 victor domingos 
 * https://no-title.victordomingos.com
 * https://github.com/victordomingos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package gui;

import core.Session;
import core.SpaceObject;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import jparsec.time.AstroDate;
import jparsec.util.JPARSECException;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;

import javax.swing.JTable;
import javax.swing.SortOrder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import jparsec.time.TimeElement;

/**
 *
 * @author victor
 */
public class Main extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    private final SessionManager session_manager = new SessionManager();
    private final LocationManager location_manager = new LocationManager();
    private final gui.panels.SessionSetupPanel lpanel = new gui.panels.SessionSetupPanel(this);
    private Session current_session;
    private String curSelectedTarget = "";
    private String curSelectedKind = "";

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();

        // Update button state when Session Manager window opens/closes --------
        session_manager.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                btn_manageSessions.setSelected(false);
            }
        });

        session_manager.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                btn_manageSessions.setSelected(true);
            }
        });

        // Update button state when Location Manager window opens/closes -------
        location_manager.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                btn_manageLocations.setSelected(false);
                lpanel.updateLocationsCombo();
            }
        });

        location_manager.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                btn_manageLocations.setSelected(true);
            }
        });

        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] selectedRows = table.getSelectedRows();
                if (selectedRows.length > 0) {
                    curSelectedTarget = (String) table.getValueAt(selectedRows[0], 0);
                    curSelectedKind = (String) table.getValueAt(selectedRows[0], 1);
                    if (curSelectedTarget.length() > 0) {
                        fillDetailsPanel(curSelectedTarget, curSelectedKind);
                    }
                }
            }
        });

        class SecondaryTableCellRenderer extends DefaultTableCellRenderer {

            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int col) {
                super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, col);
                setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
                return this;
            }
        }

        table_riseSetTransit.setDefaultRenderer(Object.class, new SecondaryTableCellRenderer());

        // Maximize window at start if the screen isn't too big.
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        double w = scrSize.getWidth();
        double h = scrSize.getHeight();
        if (w < 1920 && h < 1080) {
            this.setExtendedState(Main.MAXIMIZED_BOTH);
        } else {
            this.setSize(1600, 980);
        }
        
        leftPanel.add(lpanel);

        
        rightPanel.setVisible(false);
        btn_rightPanel.setSelected(false);
        centerBottomPanel.setVisible(false);
        btn_sessionNotes.setSelected(false);

        
        // If there is no location yet, instruct user to create a new location
        if(this.lpanel.getCmb_locationItemsCount() == 0){
            this.location_manager.setVisible(true);
            this.btn_manageLocations.setSelected(true);
            menu_toggleLocationManager.setSelected(true);
            this.location_manager.toFront();   // Why isn't it getting to front??         
        }

        //activate new location editor
        // display messagebox
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jToolBar1 = new javax.swing.JToolBar();
        btn_leftPanel = new javax.swing.JToggleButton();
        btn_manageSessions = new javax.swing.JToggleButton();
        btn_manageLocations = new javax.swing.JToggleButton();
        btn_bookmarkedOnly = new javax.swing.JToggleButton();
        btn_hideAlreadySeen = new javax.swing.JToggleButton();
        btn_sessionNotes = new javax.swing.JToggleButton();
        btn_rightPanel = new javax.swing.JToggleButton();
        centerPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        centerBottomPanel = new org.jdesktop.swingx.JXPanel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jXTextArea1 = new org.jdesktop.swingx.JXTextArea();
        rightPanel = new org.jdesktop.swingx.JXPanel();
        lbl_target_details_name = new javax.swing.JLabel();
        subpanel_photo = new org.jdesktop.swingx.JXPanel();
        lbl_photo = new javax.swing.JLabel();
        lbl_photo_declination = new javax.swing.JLabel();
        lbl_photo_ra = new javax.swing.JLabel();
        lbl_photo3 = new javax.swing.JLabel();
        lbl_photo4 = new javax.swing.JLabel();
        tabp_details = new javax.swing.JTabbedPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_info = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_riseSetTransit = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_positions = new javax.swing.JTable();
        leftPanel = new org.jdesktop.swingx.JXPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_file = new javax.swing.JMenu();
        menu_newSession = new javax.swing.JMenuItem();
        menu_saveSession = new javax.swing.JMenuItem();
        menu_calculate = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menu_newLocation = new javax.swing.JMenuItem();
        menu_quit = new javax.swing.JMenuItem();
        menu_view = new javax.swing.JMenu();
        menu_showMainWindow = new javax.swing.JMenuItem();
        menu_toggleSessionManager = new javax.swing.JCheckBoxMenuItem();
        menu_toggleLocationManager = new javax.swing.JCheckBoxMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menu_toggleLeftPanel = new javax.swing.JCheckBoxMenuItem();
        menu_toggleRightPanel = new javax.swing.JCheckBoxMenuItem();
        menu_toggleNotesPanel = new javax.swing.JCheckBoxMenuItem();
        menu_help = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        menu_about = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nice Telescope Planner");
        setMinimumSize(new java.awt.Dimension(1000, 750));
        setName("mainWindow"); // NOI18N
        setSize(new java.awt.Dimension(1024, 750));

        jToolBar1.setBackground(new java.awt.Color(204, 204, 204));
        jToolBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar1.setForeground(javax.swing.UIManager.getDefaults().getColor("ToolBar.background"));
        jToolBar1.setRollover(true);
        jToolBar1.setMaximumSize(new java.awt.Dimension(2400, 50));
        jToolBar1.setMinimumSize(new java.awt.Dimension(800, 50));
        jToolBar1.setPreferredSize(new java.awt.Dimension(1000, 50));

        btn_leftPanel.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        btn_leftPanel.setSelected(true);
        btn_leftPanel.setText("Session Setup");
        btn_leftPanel.setFocusable(false);
        btn_leftPanel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_leftPanel.setMaximumSize(new java.awt.Dimension(300, 48));
        btn_leftPanel.setMinimumSize(new java.awt.Dimension(100, 48));
        btn_leftPanel.setPreferredSize(new java.awt.Dimension(128, 48));
        btn_leftPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_leftPanelActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_leftPanel);

        btn_manageSessions.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        btn_manageSessions.setText("Manage Sessions");
        btn_manageSessions.setFocusable(false);
        btn_manageSessions.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_manageSessions.setMaximumSize(new java.awt.Dimension(300, 48));
        btn_manageSessions.setMinimumSize(new java.awt.Dimension(100, 48));
        btn_manageSessions.setPreferredSize(new java.awt.Dimension(128, 48));
        btn_manageSessions.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_manageSessions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_manageSessionsActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_manageSessions);

        btn_manageLocations.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        btn_manageLocations.setText("Manage Locations");
        btn_manageLocations.setFocusable(false);
        btn_manageLocations.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_manageLocations.setMaximumSize(new java.awt.Dimension(300, 48));
        btn_manageLocations.setMinimumSize(new java.awt.Dimension(100, 48));
        btn_manageLocations.setPreferredSize(new java.awt.Dimension(128, 48));
        btn_manageLocations.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_manageLocations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_manageLocationsActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_manageLocations);

        btn_bookmarkedOnly.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        btn_bookmarkedOnly.setText("Bookmarked Only");
        btn_bookmarkedOnly.setFocusable(false);
        btn_bookmarkedOnly.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_bookmarkedOnly.setMaximumSize(new java.awt.Dimension(300, 48));
        btn_bookmarkedOnly.setMinimumSize(new java.awt.Dimension(100, 48));
        btn_bookmarkedOnly.setPreferredSize(new java.awt.Dimension(128, 48));
        btn_bookmarkedOnly.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btn_bookmarkedOnly);

        btn_hideAlreadySeen.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        btn_hideAlreadySeen.setText("Hide Already Seen");
        btn_hideAlreadySeen.setFocusable(false);
        btn_hideAlreadySeen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_hideAlreadySeen.setMaximumSize(new java.awt.Dimension(300, 48));
        btn_hideAlreadySeen.setMinimumSize(new java.awt.Dimension(100, 48));
        btn_hideAlreadySeen.setPreferredSize(new java.awt.Dimension(128, 48));
        btn_hideAlreadySeen.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btn_hideAlreadySeen);

        btn_sessionNotes.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        btn_sessionNotes.setText("Session Notes");
        btn_sessionNotes.setFocusable(false);
        btn_sessionNotes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_sessionNotes.setMaximumSize(new java.awt.Dimension(300, 48));
        btn_sessionNotes.setMinimumSize(new java.awt.Dimension(100, 48));
        btn_sessionNotes.setPreferredSize(new java.awt.Dimension(128, 48));
        btn_sessionNotes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_sessionNotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sessionNotesActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_sessionNotes);

        btn_rightPanel.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        btn_rightPanel.setSelected(true);
        btn_rightPanel.setText("Object Details");
        btn_rightPanel.setFocusable(false);
        btn_rightPanel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_rightPanel.setMaximumSize(new java.awt.Dimension(300, 48));
        btn_rightPanel.setMinimumSize(new java.awt.Dimension(100, 48));
        btn_rightPanel.setPreferredSize(new java.awt.Dimension(128, 48));
        btn_rightPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rightPanelActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_rightPanel);

        table.setAutoCreateRowSorter(true);
        table.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Designation", "Kind", "Rise", "Set", "Constellation", "Bookmark", "Seen"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
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
        table.setRowHeight(32);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);
        table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel21.setBackground(javax.swing.UIManager.getDefaults().getColor("Nb.browser.picker.background.light"));
        jLabel21.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Session notes");

        jXTextArea1.setColumns(20);
        jXTextArea1.setRows(5);
        jScrollPane2.setViewportView(jXTextArea1);

        javax.swing.GroupLayout centerBottomPanelLayout = new javax.swing.GroupLayout(centerBottomPanel);
        centerBottomPanel.setLayout(centerBottomPanelLayout);
        centerBottomPanelLayout.setHorizontalGroup(
            centerBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerBottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane2)
        );
        centerBottomPanelLayout.setVerticalGroup(
            centerBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerBottomPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout centerPanelLayout = new javax.swing.GroupLayout(centerPanel);
        centerPanel.setLayout(centerPanelLayout);
        centerPanelLayout.setHorizontalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(centerBottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        centerPanelLayout.setVerticalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(centerBottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        rightPanel.setBackground(javax.swing.UIManager.getDefaults().getColor("Nb.browser.picker.background.light"));
        rightPanel.setPreferredSize(new java.awt.Dimension(340, 0));

        lbl_target_details_name.setBackground(javax.swing.UIManager.getDefaults().getColor("Nb.browser.picker.background.light"));
        lbl_target_details_name.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lbl_target_details_name.setForeground(new java.awt.Color(51, 51, 51));
        lbl_target_details_name.setText("Target details");

        subpanel_photo.setBackground(new java.awt.Color(0, 0, 0));
        subpanel_photo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        subpanel_photo.setPreferredSize(new java.awt.Dimension(334, 100));

        lbl_photo.setBackground(new java.awt.Color(0, 0, 0));
        lbl_photo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_photo.setText("[No picture available.]");

        lbl_photo_declination.setBackground(new java.awt.Color(0, 0, 0));
        lbl_photo_declination.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lbl_photo_declination.setForeground(new java.awt.Color(255, 255, 255));
        lbl_photo_declination.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_photo_declination.setText("Dec.: ");

        lbl_photo_ra.setBackground(new java.awt.Color(0, 0, 0));
        lbl_photo_ra.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lbl_photo_ra.setForeground(new java.awt.Color(255, 255, 255));
        lbl_photo_ra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_photo_ra.setText("RA: ");

        lbl_photo3.setBackground(new java.awt.Color(0, 0, 0));
        lbl_photo3.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lbl_photo3.setForeground(new java.awt.Color(255, 255, 255));
        lbl_photo3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_photo3.setText("Mag.: ");

        lbl_photo4.setBackground(new java.awt.Color(0, 0, 0));
        lbl_photo4.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lbl_photo4.setForeground(new java.awt.Color(255, 255, 255));
        lbl_photo4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_photo4.setText("Angular Radius: ");

        javax.swing.GroupLayout subpanel_photoLayout = new javax.swing.GroupLayout(subpanel_photo);
        subpanel_photo.setLayout(subpanel_photoLayout);
        subpanel_photoLayout.setHorizontalGroup(
            subpanel_photoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subpanel_photoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(subpanel_photoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_photo3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_photo4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(subpanel_photoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_photo_ra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_photo_declination, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
            .addGroup(subpanel_photoLayout.createSequentialGroup()
                .addComponent(lbl_photo, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        subpanel_photoLayout.setVerticalGroup(
            subpanel_photoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subpanel_photoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbl_photo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(subpanel_photoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_photo_ra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_photo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(subpanel_photoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_photo_declination, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_photo4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(570, 570, 570))
        );

        tabp_details.setToolTipText("");
        tabp_details.setMaximumSize(new java.awt.Dimension(350, 32767));
        tabp_details.setPreferredSize(new java.awt.Dimension(350, 429));

        table_info.setAutoCreateRowSorter(true);
        table_info.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Characteristic", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_info.setMaximumSize(new java.awt.Dimension(2147483647, 640));
        table_info.setMinimumSize(new java.awt.Dimension(110, 32));
        table_info.setRowHeight(20);
        table_info.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(table_info);
        table_info.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        tabp_details.addTab("Info", jScrollPane5);

        table_riseSetTransit.setAutoCreateRowSorter(true);
        table_riseSetTransit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event", "Date/Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_riseSetTransit.setColumnSelectionAllowed(true);
        table_riseSetTransit.setMaximumSize(new java.awt.Dimension(320, 640));
        table_riseSetTransit.setMinimumSize(new java.awt.Dimension(110, 32));
        table_riseSetTransit.setRowHeight(20);
        table_riseSetTransit.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(table_riseSetTransit);
        table_riseSetTransit.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (table_riseSetTransit.getColumnModel().getColumnCount() > 0) {
            table_riseSetTransit.getColumnModel().getColumn(0).setPreferredWidth(60);
            table_riseSetTransit.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        tabp_details.addTab("Rise/Set/Transit", jScrollPane4);

        table_positions.setAutoCreateRowSorter(true);
        table_positions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Altitude", "Azimuth"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_positions.setMaximumSize(new java.awt.Dimension(2147483647, 640));
        table_positions.setMinimumSize(new java.awt.Dimension(110, 32));
        table_positions.setRowHeight(20);
        table_positions.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(table_positions);
        table_positions.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (table_positions.getColumnModel().getColumnCount() > 0) {
            table_positions.getColumnModel().getColumn(2).setHeaderValue("Azimuth");
        }

        tabp_details.addTab("Positions", jScrollPane3);

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rightPanelLayout.createSequentialGroup()
                        .addComponent(lbl_target_details_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(386, 386, 386))
                    .addGroup(rightPanelLayout.createSequentialGroup()
                        .addComponent(tabp_details, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addComponent(subpanel_photo, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addGap(169, 169, 169))
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_target_details_name, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subpanel_photo, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tabp_details, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        leftPanel.setBackground(javax.swing.UIManager.getDefaults().getColor("Nb.browser.picker.background.light"));
        leftPanel.setPreferredSize(new java.awt.Dimension(350, 580));
        leftPanel.setLayout(new java.awt.GridLayout(1, 1));

        menu_file.setText("File");

        menu_newSession.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.META_MASK));
        menu_newSession.setText("New session (start over)");
        menu_newSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_newSessionActionPerformed(evt);
            }
        });
        menu_file.add(menu_newSession);

        menu_saveSession.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.META_MASK));
        menu_saveSession.setText("Save current session");
        menu_saveSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_saveSessionActionPerformed(evt);
            }
        });
        menu_file.add(menu_saveSession);

        menu_calculate.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.META_MASK));
        menu_calculate.setText("Calculate session visibility");
        menu_calculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_calculateActionPerformed(evt);
            }
        });
        menu_file.add(menu_calculate);
        menu_file.add(jSeparator2);

        menu_newLocation.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.META_MASK));
        menu_newLocation.setText("New location");
        menu_file.add(menu_newLocation);

        menu_quit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.META_MASK));
        menu_quit.setText("Quit Nice Telescope Planner");
        menu_file.add(menu_quit);

        jMenuBar1.add(menu_file);

        menu_view.setText("View");

        menu_showMainWindow.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.META_MASK));
        menu_showMainWindow.setText("Main Window (bring to front)");
        menu_view.add(menu_showMainWindow);

        menu_toggleSessionManager.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.META_MASK));
        menu_toggleSessionManager.setText("Session Manager");
        menu_toggleSessionManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_toggleSessionManagerActionPerformed(evt);
            }
        });
        menu_view.add(menu_toggleSessionManager);

        menu_toggleLocationManager.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.META_MASK));
        menu_toggleLocationManager.setText("Location Manager");
        menu_toggleLocationManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_toggleLocationManagerActionPerformed(evt);
            }
        });
        menu_view.add(menu_toggleLocationManager);
        menu_view.add(jSeparator1);

        menu_toggleLeftPanel.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.META_MASK));
        menu_toggleLeftPanel.setSelected(true);
        menu_toggleLeftPanel.setText("Session setup configurator (left panel)");
        menu_toggleLeftPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_toggleLeftPanelActionPerformed(evt);
            }
        });
        menu_view.add(menu_toggleLeftPanel);

        menu_toggleRightPanel.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.META_MASK));
        menu_toggleRightPanel.setText("Object details (right panel)");
        menu_toggleRightPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_toggleRightPanelActionPerformed(evt);
            }
        });
        menu_view.add(menu_toggleRightPanel);

        menu_toggleNotesPanel.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, java.awt.event.InputEvent.META_MASK));
        menu_toggleNotesPanel.setText("Session notes");
        menu_toggleNotesPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_toggleNotesPanelActionPerformed(evt);
            }
        });
        menu_view.add(menu_toggleNotesPanel);

        jMenuBar1.add(menu_view);

        menu_help.setText("Help");

        jMenuItem8.setText("How to use");
        menu_help.add(jMenuItem8);

        jMenuItem6.setText("Report an issue");
        menu_help.add(jMenuItem6);

        jMenuItem7.setText("Visit the author's website");
        menu_help.add(jMenuItem7);
        menu_help.add(jSeparator4);

        jMenu6.setText("Useful Wikipedia pages");

        jMenuItem17.setText("Solar System");
        jMenu6.add(jMenuItem17);

        jMenuItem18.setText("Constellations");
        jMenu6.add(jMenuItem18);

        jMenuItem15.setText("Limiting magnitude");
        jMenu6.add(jMenuItem15);

        jMenuItem16.setText("Coordinate systems");
        jMenu6.add(jMenuItem16);

        menu_help.add(jMenu6);
        menu_help.add(jSeparator3);

        menu_about.setText("About Nice Telescope Planner");
        menu_help.add(menu_about);

        jMenuBar1.add(menu_help);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(centerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(centerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
                    .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Populate the Details panel
     *
     * @param target
     * @param kind
     */
    private void fillDetailsPanel(String target, String kind) {
        if (!rightPanel.isVisible()) {
            rightPanel.setVisible(true);
            this.btn_rightPanel.setSelected(true);
            menu_toggleRightPanel.setSelected(true);
        }

        SpaceObject obj = this.current_session.getTarget(target);

        // Populate the image section ========================================
        // Images must have the right proportions (330x160), 
        // since we are not doing proportional resizing here.
        Icon img = gui.ImageLoader.getScaledSpaceImage(target, lbl_photo);
        if (img != null) {
            lbl_photo.setIcon(img);
            lbl_photo.setText("");
        } else {
            lbl_photo.setText("[No picture available.]");
            lbl_photo.setIcon(null);
        }

        // Populate white labels below image 
        // Populate the Info panel ==========================================
        // Populate the Rise/Set/transit table ==============================
        // Set columns headers and table Model for rise/set/transit - make it non-editable
        String cols[] = {"Event", "Date/time"};
        DefaultTableModel RSTTableModel = new DefaultTableModel(cols, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };
        table_riseSetTransit.setModel(RSTTableModel);

        for (Double rise : obj.getRises()) {
            try {
                String dt = (new AstroDate(rise))
                        .toString(-1)
                        .replace(" ", "   ");
                Object[] data = {"Rise", dt};
                RSTTableModel.addRow(data);
            } catch (JPARSECException ex) {
                System.out.println(ex);
            }
        }
        for (Double set : obj.getSets()) {
            try {
                String dt = (new AstroDate(set))
                        .toString(-1)
                        .replace(" ", "   ");
                Object[] data = {"Set", dt};
                RSTTableModel.addRow(data);
            } catch (JPARSECException ex) {
                System.out.println(ex);
            }
        }
        for (Double transit : obj.getTransits()) {
            try {
                String dt = (new AstroDate(transit))
                        .toString(-1)
                        .replace(" ", "   ");
                Object[] data = {"Transit", dt};
                RSTTableModel.addRow(data);
            } catch (JPARSECException ex) {
                System.out.println(ex);
            }
        }
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) table_riseSetTransit.getModel());
        table_riseSetTransit.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.sort();

        // Populate the Positions table =====================================
        String cols_pos[] = {"Time", "Altitude", "Azimuth"};
        DefaultTableModel PositionsTableModel = new DefaultTableModel(cols_pos, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };
        table_positions.setModel(PositionsTableModel);
        for (TimeElement t : obj.getHourlyTimeElements()) {
            try {
                String dt = t.astroDate.toStringTZ().substring(11, 16);
                Object[] data = {dt, obj.getAlt(t), obj.getAz(t)};
                PositionsTableModel.addRow(data);
            } catch (JPARSECException ex) {
                System.out.println(ex);
            }
        }
    }

    public JToggleButton getBtn_manageLocations() {
        return btn_manageLocations;
    }

    public JToggleButton getBtn_manageSessions() {
        return btn_manageSessions;
    }

    public void applySessionSettings() {
        this.updateTable();
        if(table.getRowCount()>0){
            table.setRowSelectionInterval(0, 0);
        }
    }

    private void updateTable() {
        String designation, kind, rise, set, constellation;
        Boolean bookmark, seen;

        // get all targets 
        this.current_session = new Session(lpanel.getCurSelectedLocation(),
                lpanel.getStartDatetime(), lpanel.getEndDatetime(),
                lpanel.getLimitingMagnitude(), lpanel.getAtConstellation(),
                lpanel.getOnlyKind());

        //Set columns headers and table Model - make it non-editable
        String cols[] = {"Designation", "Kind", "Rise", "Set",
            "Constellation", "Bookmark", "Seen"};

        DefaultTableModel SessionTableModel = new DefaultTableModel(cols, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public Class getColumnClass(int col) {
                return (col == 5 || col == 6) ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                return (col == 5 || col == 6);
            }
        };
        
            SessionTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getType() == TableModelEvent.UPDATE){
                    System.out.println(e.getFirstRow());
                    System.out.println(e.getColumn());
                }
            }
        });
            
        table.setModel(SessionTableModel);
        table.setDefaultRenderer(Object.class, new MainTableCellRenderer());
        table.setRowSelectionAllowed(true);

        // add location records to table
        for (SpaceObject t : current_session.getTargets()) {
            designation = t.getName();
            kind = t.getKind();
            rise = makeDateTimeString(t.getRises().get(0));
            set = makeDateTimeString(t.getSets().get(0));
            constellation = t.getConstell();
            
            bookmark = false;   // TODO
            seen = false;       // TODO
            
            Object[] data = {designation, kind, rise, set, constellation,
                bookmark, seen};
            SessionTableModel.addRow(data);
        }
    }

    /**
     * Build a formatted date string from a JD double (Rise/set)
     *
     * @param date
     * @return string
     */
    private String makeDateTimeString(Double date) {
        String y, M, d, h, m;
        try {
            AstroDate rdt = new AstroDate(date);

            // PORTO 21/10 - 22h00 rise list DEBUG!!! timezone?
            //y = Integer.toString(rdt.getYear());
            M = new DecimalFormat("00").format(rdt.getMonth());
            d = new DecimalFormat("00").format(rdt.getDay());
            h = new DecimalFormat("00").format(rdt.getHour());
            m = new DecimalFormat("00").format(rdt.getRoundedMinute());
            //rise = y + "/" + M + "/" + d + " " + h + ":" + m;
            return (M + "/" + d + " " + h + "h" + m);
        } catch (JPARSECException | java.lang.IndexOutOfBoundsException e) {
            return "N/A";
        }
    }

    private void btn_leftPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_leftPanelActionPerformed
        this.leftPanel.setVisible(this.btn_leftPanel.isSelected());
        this.menu_toggleLeftPanel.setSelected(this.btn_leftPanel.isSelected());
    }//GEN-LAST:event_btn_leftPanelActionPerformed

    private void btn_rightPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rightPanelActionPerformed
        this.rightPanel.setVisible(this.btn_rightPanel.isSelected());
        this.menu_toggleRightPanel.setSelected(this.btn_rightPanel.isSelected());

    }//GEN-LAST:event_btn_rightPanelActionPerformed

    private void btn_sessionNotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sessionNotesActionPerformed
        this.centerBottomPanel.setVisible(this.btn_sessionNotes.isSelected());
        this.menu_toggleNotesPanel.setSelected(this.btn_sessionNotes.isSelected());
    }//GEN-LAST:event_btn_sessionNotesActionPerformed

    private void btn_manageSessionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_manageSessionsActionPerformed
        this.session_manager.setVisible(this.btn_manageSessions.isSelected());
        this.menu_toggleSessionManager.setSelected(this.btn_manageSessions.isSelected());
    }//GEN-LAST:event_btn_manageSessionsActionPerformed

    private void btn_manageLocationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_manageLocationsActionPerformed
        this.location_manager.setVisible(this.btn_manageLocations.isSelected());
        this.menu_toggleLocationManager.setSelected(this.btn_manageLocations.isSelected());
    }//GEN-LAST:event_btn_manageLocationsActionPerformed

    private void menu_calculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_calculateActionPerformed
       applySessionSettings();
    }//GEN-LAST:event_menu_calculateActionPerformed

    private void menu_saveSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_saveSessionActionPerformed
        
    }//GEN-LAST:event_menu_saveSessionActionPerformed

    private void menu_newSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_newSessionActionPerformed
        lpanel.clearSessionSetupPanel();
    }//GEN-LAST:event_menu_newSessionActionPerformed

    private void menu_toggleSessionManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_toggleSessionManagerActionPerformed
        btn_manageSessions.setSelected(!btn_manageSessions.isSelected());
        session_manager.setVisible(btn_manageSessions.isSelected());
        menu_toggleSessionManager.setSelected(btn_manageSessions.isSelected());
    }//GEN-LAST:event_menu_toggleSessionManagerActionPerformed

    private void menu_toggleLocationManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_toggleLocationManagerActionPerformed
        btn_manageLocations.setSelected(!btn_manageLocations.isSelected());
        location_manager.setVisible(btn_manageLocations.isSelected());
        menu_toggleLocationManager.setSelected(btn_manageLocations.isSelected());
    }//GEN-LAST:event_menu_toggleLocationManagerActionPerformed

    private void menu_toggleNotesPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_toggleNotesPanelActionPerformed
        btn_sessionNotes.setSelected(!btn_sessionNotes.isSelected());
        centerBottomPanel.setVisible(btn_sessionNotes.isSelected());
        menu_toggleNotesPanel.setSelected(btn_sessionNotes.isSelected());
    }//GEN-LAST:event_menu_toggleNotesPanelActionPerformed

    private void menu_toggleRightPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_toggleRightPanelActionPerformed
        btn_rightPanel.setSelected(!btn_rightPanel.isSelected());
        rightPanel.setVisible(btn_rightPanel.isSelected());
        menu_toggleRightPanel.setSelected(btn_rightPanel.isSelected());        // TODO add your handling code here:
    }//GEN-LAST:event_menu_toggleRightPanelActionPerformed

    private void menu_toggleLeftPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_toggleLeftPanelActionPerformed
        btn_leftPanel.setSelected(!btn_leftPanel.isSelected());
        leftPanel.setVisible(this.btn_leftPanel.isSelected());
        menu_toggleLeftPanel.setSelected(this.btn_leftPanel.isSelected());
    }//GEN-LAST:event_menu_toggleLeftPanelActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>
        // Set alternating row colors for jTable objects.
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        if (defaults.get("Table.alternateRowColor") == null) {
            defaults.put("Table.alternateRowColor", new Color(247, 247, 247));
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_bookmarkedOnly;
    private javax.swing.JToggleButton btn_hideAlreadySeen;
    private javax.swing.JToggleButton btn_leftPanel;
    private javax.swing.JToggleButton btn_manageLocations;
    private javax.swing.JToggleButton btn_manageSessions;
    private javax.swing.JToggleButton btn_rightPanel;
    private javax.swing.JToggleButton btn_sessionNotes;
    private org.jdesktop.swingx.JXPanel centerBottomPanel;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JToolBar jToolBar1;
    private org.jdesktop.swingx.JXTextArea jXTextArea1;
    private javax.swing.JLabel lbl_photo;
    private javax.swing.JLabel lbl_photo3;
    private javax.swing.JLabel lbl_photo4;
    private javax.swing.JLabel lbl_photo_declination;
    private javax.swing.JLabel lbl_photo_ra;
    private javax.swing.JLabel lbl_target_details_name;
    private org.jdesktop.swingx.JXPanel leftPanel;
    private javax.swing.JMenuItem menu_about;
    private javax.swing.JMenuItem menu_calculate;
    private javax.swing.JMenu menu_file;
    private javax.swing.JMenu menu_help;
    private javax.swing.JMenuItem menu_newLocation;
    private javax.swing.JMenuItem menu_newSession;
    private javax.swing.JMenuItem menu_quit;
    private javax.swing.JMenuItem menu_saveSession;
    private javax.swing.JMenuItem menu_showMainWindow;
    private javax.swing.JCheckBoxMenuItem menu_toggleLeftPanel;
    private javax.swing.JCheckBoxMenuItem menu_toggleLocationManager;
    private javax.swing.JCheckBoxMenuItem menu_toggleNotesPanel;
    private javax.swing.JCheckBoxMenuItem menu_toggleRightPanel;
    private javax.swing.JCheckBoxMenuItem menu_toggleSessionManager;
    private javax.swing.JMenu menu_view;
    private org.jdesktop.swingx.JXPanel rightPanel;
    private org.jdesktop.swingx.JXPanel subpanel_photo;
    private javax.swing.JTable table;
    private javax.swing.JTable table_info;
    private javax.swing.JTable table_positions;
    private javax.swing.JTable table_riseSetTransit;
    private javax.swing.JTabbedPane tabp_details;
    // End of variables declaration//GEN-END:variables
}

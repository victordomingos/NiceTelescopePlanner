/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import core.Session;
import core.SpaceObject;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import javax.swing.JToggleButton;
import javax.swing.SwingWorker;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import jparsec.time.AstroDate;
import jparsec.util.JPARSECException;

/**
 *
 * @author victor
 */
public class Main extends javax.swing.JFrame {

    private final SessionManager session_manager = new SessionManager();
    private final LocationManager location_manager = new LocationManager();
    private final gui.panels.SessionSetupPanel lpanel = new gui.panels.SessionSetupPanel(this);
    private Session current_session;
    
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
        
        
        rightPanel.setVisible(false);
        leftPanel.add(lpanel);
        btn_rightPanel.setSelected(false);

        centerBottomPanel.setVisible(false);
        btn_sessionNotes.setSelected(false);

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
        btn_leftPanel = new javax.swing.JToggleButton();
        btn_manageSessions = new javax.swing.JToggleButton();
        btn_manageLocations = new javax.swing.JToggleButton();
        btn_includePlanets = new javax.swing.JToggleButton();
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
        jLabel16 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        leftPanel = new org.jdesktop.swingx.JXPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nice Telescope Planner");
        setMinimumSize(new java.awt.Dimension(1000, 750));
        setName("mainWindow"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1280, 800));
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

        btn_includePlanets.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        btn_includePlanets.setSelected(true);
        btn_includePlanets.setText("Include Planets");
        btn_includePlanets.setFocusable(false);
        btn_includePlanets.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_includePlanets.setMaximumSize(new java.awt.Dimension(300, 48));
        btn_includePlanets.setMinimumSize(new java.awt.Dimension(100, 48));
        btn_includePlanets.setPreferredSize(new java.awt.Dimension(128, 48));
        btn_includePlanets.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btn_includePlanets);

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
        table.setColumnSelectionAllowed(true);
        table.setMaximumSize(new java.awt.Dimension(2147483647, 640));
        table.setMinimumSize(new java.awt.Dimension(110, 32));
        table.setRowHeight(32);
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(centerBottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        rightPanel.setBackground(javax.swing.UIManager.getDefaults().getColor("Nb.browser.picker.background.light"));
        rightPanel.setPreferredSize(new java.awt.Dimension(340, 0));

        jLabel16.setBackground(javax.swing.UIManager.getDefaults().getColor("Nb.browser.picker.background.light"));
        jLabel16.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Target details");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("[There is no target object selected yet.]");

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rightPanelLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        leftPanel.setBackground(javax.swing.UIManager.getDefaults().getColor("Nb.browser.picker.background.light"));
        leftPanel.setPreferredSize(new java.awt.Dimension(350, 580));
        leftPanel.setLayout(new java.awt.GridLayout(1, 1));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

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
                    .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
                    .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JToggleButton getBtn_manageLocations() {
        return btn_manageLocations;
    }

    public JToggleButton getBtn_manageSessions() {
        return btn_manageSessions;
    }

    public void applySessionSettings(){
        this.updateTable();        
    }
    
    
    private void updateTable() {
        String designation, kind, rise, set, constellation;
        Boolean bookmark, seen;
        
        
        // get all targets 
        this.current_session = new Session(lpanel.getCurSelectedLocation(), 
                lpanel.getStartDatetime(), lpanel.getEndDatetime(),
                lpanel.getLimitingMagnitude());
        
        //Set columns headers and table Model - make it non-editable
        String columns[] = {"Designation", "Kind", "Rise", "Set", 
                "Constellation", "Bookmark", "Seen"};

        DefaultTableModel SessionTableModel = new DefaultTableModel(columns, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };

        
        table.setModel(SessionTableModel);
        
        
        // add location records to table
        String y, M, d, h, m;
        for (SpaceObject t : current_session.getTargets()) {
            designation = t.getName();
            kind = t.getKind();
            
            try{
                AstroDate rdt = new AstroDate(t.getRises()[0]);
                //y = Integer.toString(rdt.getYear());
                M = new DecimalFormat("00").format(rdt.getMonth());
                d = new DecimalFormat("00").format(rdt.getDay());
                h = new DecimalFormat("00").format(rdt.getHour());
                m = new DecimalFormat("00").format(rdt.getRoundedMinute());
                //rise = y + "/" + M + "/" + d + " " + h + ":" + m;
                rise = M + "/" + d + " " + h + "h" + m;
            }
            catch (JPARSECException e){
                rise = "N/A";
            }
                              
            try{
                AstroDate sdt = new AstroDate(t.getSets()[0]);
                //y = Integer.toString(sdt.getYear());
                M = new DecimalFormat("00").format(sdt.getMonth());
                d = new DecimalFormat("00").format(sdt.getDay());
                h = new DecimalFormat("00").format(sdt.getHour());
                m = new DecimalFormat("00").format(sdt.getRoundedMinute());
                //set = y + "/" + M + "/" + d + " " + h + ":" + m;
                set = M + "/" + d + " " + h + "h" + m;
            }
            catch (JPARSECException e){
                set = "N/A";
            }
            
            constellation = t.getConstellation();
        
            bookmark = false;   // TODO
            seen = false;       // TODO

            Object[] data = {designation, kind, rise, set, constellation, bookmark, seen};
            SessionTableModel.addRow(data);
        }

        // Set basic table formatting
//        table.getColumn("Designation").setMaxWidth(160);
//        table.getColumn("Kind").setMaxWidth(160);
//        table.getColumn("Rise").setMaxWidth(80);
//        table.getColumn("Set").setMaxWidth(80);
//        table.getColumn("Constellation").setPreferredWidth(50);
//        table.getColumn("Bookmark").setPreferredWidth(160);
//        table.getColumn("Seen").setPreferredWidth(160);
    }

    private void btn_leftPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_leftPanelActionPerformed
        this.lpanel.setVisible(this.btn_leftPanel.isSelected());
    }//GEN-LAST:event_btn_leftPanelActionPerformed

    private void btn_rightPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rightPanelActionPerformed
        this.rightPanel.setVisible(this.btn_rightPanel.isSelected());

    }//GEN-LAST:event_btn_rightPanelActionPerformed

    private void btn_sessionNotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sessionNotesActionPerformed
        this.centerBottomPanel.setVisible(this.btn_sessionNotes.isSelected());
    }//GEN-LAST:event_btn_sessionNotesActionPerformed

    private void btn_manageSessionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_manageSessionsActionPerformed
        this.session_manager.setVisible(this.btn_manageSessions.isSelected());
    }//GEN-LAST:event_btn_manageSessionsActionPerformed

    private void btn_manageLocationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_manageLocationsActionPerformed
        this.location_manager.setVisible(this.btn_manageLocations.isSelected());
    }//GEN-LAST:event_btn_manageLocationsActionPerformed

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
    private javax.swing.JToggleButton btn_includePlanets;
    private javax.swing.JToggleButton btn_leftPanel;
    private javax.swing.JToggleButton btn_manageLocations;
    private javax.swing.JToggleButton btn_manageSessions;
    private javax.swing.JToggleButton btn_rightPanel;
    private javax.swing.JToggleButton btn_sessionNotes;
    private org.jdesktop.swingx.JXPanel centerBottomPanel;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private org.jdesktop.swingx.JXTextArea jXTextArea1;
    private org.jdesktop.swingx.JXPanel leftPanel;
    private org.jdesktop.swingx.JXPanel rightPanel;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}

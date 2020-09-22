!********************************   Toolkit   *********************************
!jlink_java_command c:\applic\jre\bin\java.exe -DcipDebugAll="true" 
jlink_java_command c:\applic\jre\bin\java.exe -Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,address=8013,server=y,suspend=n
!jlink_java_command c:\applic\jre\bin\java.exe
!protkdat c:\Users\skvarkaj\Documents\creosout\protk.dat
protkdat c:\Users\skvarkaj\Documents\ulet\build\protk.dat
protkdat \\10.2.0.22\mechanika\.applic\avsys\toolkit\AVS-Utils\protk.dat
!******************************   Start Party   *******************************
start_model_dir \\10.2.0.22\mechanika\.applic\avsys\resources\start_models
template_designasm \\10.2.0.22\mechanika\.applic\avsys\resources\start_models\start_asm_avsys.asm
template_sheetmetalpart \\10.2.0.22\mechanika\.applic\avsys\resources\start_models\start_smt_avsys.prt
template_solidpart \\10.2.0.22\mechanika\.applic\avsys\resources\start_models\start_prt_avsys.prt
template_mfgnc \\10.2.0.22\mechanika\.applic\avsys\resources\start_models\start_mfg_nc_avsys.mfg
template_mfgmold \\10.2.0.22\mechanika\.applic\avsys\resources\start_models\start_mfg_mold_avsys.mfg
template_drawing $PRO_DIRECTORY\templates\c_drawing.drw

!********************************  Drawings   *********************************
drawing_setup_file \\10.2.0.22\mechanika\.applic\avsys\config\stn.dtl
format_setup_file \\10.2.0.22\mechanika\.applic\avsys\config\stn.dtl
model_detail_options_file \\10.2.0.22\mechanika\.applic\avsys\config\stn-3D.dtl
pen_table_file \\10.2.0.22\mechanika\.applic\avsys\config\pentable.pnt
pro_dtl_setup_dir \\10.2.0.22\mechanika\.applic\avsys\config
pro_format_dir \\10.2.0.22\mechanika\.applic\avsys\resources\formats
symbol_instance_palette_file \\10.2.0.22\mechanika\.applic\avsys\resources\symbols\symbol_gallery.drw

!*********************************  Library   *********************************
pro_library_dir \\10.2.0.22\mechanika\.applic\avsys\library
search_path_file \\10.2.0.22\mechanika\.applic\avsys\config\search.pro
texture_search_path \\10.2.0.22\mechanika\.applic\avsys\resources\textures

!********************************  Appearance   *******************************
system_colors_file \\10.2.0.22\mechanika\.applic\avsys\config\syscol.scl
pro_colormap_path \\10.2.0.22\mechanika\.applic\avsys\config

!*********************************  Symbols   *********************************
pro_surface_finish_dir \\10.2.0.22\mechanika\.applic\avsys\resources\symbols
pro_symbol_dir \\10.2.0.22\mechanika\.applic\avsys\resources\symbols

!******************************  Miscellaneous   ******************************
pro_crosshatch_dir \\10.2.0.22\mechanika\.applic\avsys\resources\crosshatch
pro_font_dir \\10.2.0.22\mechanika\.applic\avsys\resources\font
pro_group_dir \\10.2.0.22\mechanika\.applic\avsys\resources\udf
pro_material_dir \\10.2.0.22\mechanika\.applic\avsys\resources\materials
pro_plot_config_dir \\10.2.0.22\mechanika\.applic\avsys\config
pro_note_dir \\10.2.0.22\mechanika\.applic\avsys\resources\notes
pro_sheet_met_dir \\10.2.0.22\mechanika\.applic\avsys\resources\sheet_metal_tables\sheet_metal_tables
hole_parameter_file_path \\10.2.0.22\mechanika\.applic\avsys\resources\hole_tables\
intf_profile_dir \\10.2.0.22\mechanika\.applic\avsys\config
plot_file_dir \\10.2.0.22\mechanika\.applic\avsys\config
regen_backup_directory \\10.2.0.22\mechanika\.applic\avsys\folders\temp
sketcher_palette_path \\10.2.0.22\mechanika\.applic\avsys\resources\sketch_palette
tolerance_table_dir \\10.2.0.22\mechanika\.applic\avsys\resources\tol_tables\iso
web_browser_homepage \\10.2.0.22\mechanika\.applic\avsys\resources\browser\tabulky\avs_tabulky.html

!*******************************************************************************
!************************************   A   ************************************
auto_locate_in_tree no
auto_customization_propagate no
accuracy_lower_bound 1e-6
allow_redo_intersections yes
allow_rfs_default_gtols_always yes
angular_tol_0.0 5
angular_tol_0.00 5
angular_tol_0.000 5
auto_associate_dimensions yes
autobuildz_enabled yes
allow_move_view_with_move yes
atb_show_log off
add_weld_mp no
auto_center_dimension yes
!************************************   B   ************************************
!************************************   C   ************************************
!************************************   D   ************************************
default_abs_accuracy 0.0001
display_full_object_path yes
default_chamfer_scheme 45xd
dm_remember_server no
dxf_export_format 2000
dwg_export_format 2000
dxf_block_to_pro_symbol yes
!************************************   E   ************************************
enable_component_interfaces yes
enable_flat_state yes
enable_flexmove_tool yes
enable_local_extend_variations yes
enable_3dmodelspace_browser_tab no
!************************************   F   ************************************
frt_enabled yes
flip_arrow_scale 1
file_open_default_folder working_directory
!force_new_file_options_dialog yes
!************************************   G   ************************************
gtol_dim_placement under_value
gct_enabled no
!************************************   H   ************************************
highlight_new_dims YES
hlr_for_quilts yes
hole_diameter_override yes
!************************************   I   ************************************
iges_in_group_to_dwg_layer yes
iges_out_trim_curve_deviation 0.001
info_output_mode CHOOSE
initial_bend_y_factor 0.5
intf2d_out_cgm_ver 3
intf3d_in_close_open_boundaries YES
intf3d_out_surface_deviation 0.001
intf_in_treat_polyline_as single_spline
INTF_IN_USE_TEMPLATE_MODELS yes
!************************************   J   ************************************
!************************************   K   ************************************
keep_info_datums NO
!************************************   L   ************************************
linear_tol_0.00 2
linear_tol_0.000 2
linear_tol_0.0 2
!************************************   M   ************************************
menu_show_instances no
model_grid_spacing 10
multiple_skeletons_allowed yes
mass_property_calculate automatic
max_animation_time 0.3
!************************************   N   ************************************
native_kbd_macros yes
!************************************   O   ************************************
!open_simplified_rep_by_default no
open_window_maximized yes
orientation isometric
open_protk_unsigned_apps always
!************************************   P   ************************************
parenthesize_ref_dim YES
plot_names yes
pro_editor_command notepad
pro_unit_length UNIT_MM
pro_unit_mass UNIT_KILOGRAM
provide_pick_message_always yes
proe_memory_buffer_size 200
pdf_use_pentable yes
!************************************   R   ************************************
rename_drawings_with_object BOTH
reserved_menu_space 1
raster_plot_dpi 300
!************************************   S   ************************************
save_clipped_view yes
save_display yes
!select_hidden_edges_in_dwg yes
selection_of_removed_entities YES
!show_shaded_edges yes
simprep_ondemand_cleanup remove_and_erase
sketcher_refit_after_dim_modify no
sketcher_undo_reorient_view yes
spin_with_part_entities YES
system_edge_high_color 40 100 100
spin_with_orientation_center no
spin_center_display no
sketcher_animated_modify no
sketcher_dimension_autolock yes
show_axes_by_view_scope all_sub_models
sketcher_snap_model_geometry no
!************************************   T   ************************************
tangent_edge_display phantom
tiff_compression g4
tiff_type mono
todays_date_note_format %dd.%mm.%yy
tolerance_class MEDIUM
tolerance_standard ISO
topobus_enable yes
ttf_handling_for_plot_files stroke all fonts
!************************************   U   ************************************
use_8_plotter_pens yes
use_temp_dir_for_inst yes
!************************************   V   ************************************
variable_plots_in_inches NO
visible_message_lines 2
!************************************   W   ************************************
weld_ui_standard iso
web_browser_history_days 100
web_max_mail_size 5000
www_multiple_views all
weld_color 0 0 49
!********************************   GENERAL   **********************************
kbd_cmd_abbreviation on
model_rename_template _new
compress_output_files YES
windows_scale 1
feature_create_auto_begin YES
feature_create_auto_ok YES
!*****************************   DISPLAY SETTINGS   ****************************
ang_dim_in_screen YES
axis_display NO
datum_point_display no
display shadewithedges
display_annotations no
display_coordinate_sys NO
display_planes no
spin_control DRAG
show_axes_for_extr_arcs YES
!********************************   LAYERS   ***********************************
!*******************************   SHADING   ***********************************
default_ramp_size 32
save_triangles_flag YES
lods_enabled no
!*******************************   STORAGE   ***********************************
!prompt_on_erase_not_disp NO
save_objects CHANGED
!*******************************   EDITING   ***********************************
terminal_command %SystemRoot%\system32\cmd.exe
!*****************************   INTERFACES   **********************************
fix_boundaries_on_import YES
!*******************************   DRAWING   ***********************************
auto_regen_views no
default_draw_scale 1
draw_points_in_model_units YES
make_parameters_from_fmt_tables YES
sym_leader_orient_move_text YES
!******************************   Web-link   ***********************************
web_enable_javascript on
web_link_file_read yes
web_link_file_write yes
web_link_proe_read yes
web_link_proe_write yes
!************************   Creo2 Config.pro Options   *************************
allow_edit_gprf yes
auto_add_remove yes
load_ui_customization_run_dir yes
sketcher_starts_in_2d yes
windows_browser_type chromium_browser
show_preview_default remove
!*******************************************************************************
display_axes no
!********************************   LAYERS   ***********************************
def_layer layer_csys 03_KSYS
def_layer layer_datum 04_ROVINY
def_layer layer_axis 05_OSI
def_layer layer_point 06_BODY
def_layer layer_curve 07_KRIVKY
def_layer layer_surface 08_PLOCHY
def_layer layer_quilt 09_QUILTY
def_layer layer_hole_feat 10_DIERY
def_layer layer_thread_feat 11_ZAVITY
def_layer layer_cosm_sketch 12_KOZMETICKE_PRVKY
def_layer layer_copy_geom_feat 13_KOPIROVANA_GEOM
def_layer layer_ext_copy_geom_feat 14_EXT_KOPIROVANA_GEOM
def_layer layer_skeleton_model 15_SKELETONY
def_layer layer_weld_feat weld 16_ZVARY
!*******************************************************************************
!************************   Administrator MAPKEY   *****************************
!*******************************************************************************
mapkey material @MAPKEY_NAMEOpen window to choose material.;\
mapkey(continued) @MAPKEY_LABELMaterial;~ Close `main_dlg_cur` `appl_casc`;\
mapkey(continued) ~ Command `ProCmdMmModelProperties` ;\
mapkey(continued) ~ Activate `mdlprops_dlg` `MATERIAL_lay_Controls.push_Change.lay_instance`;
!
mapkey pu @MAPKEY_NAMEVymazanie starých verzií v aktuálnom pracovnom \
mapkey(continued) adresári;@MAPKEY_LABELVymazanie starých verzií;@SYSTEMpurge;
!
mapkey qw @MAPKEY_NAMEquit window;@MAPKEY_LABELquit window;\
mapkey(continued) ~ Select `main_dlg_cur` `MenuBar1`1  `Windows`;\
mapkey(continued) ~ Close `main_dlg_cur` `MenuBar1`;\
mapkey(continued) ~ Activate `main_dlg_cur` `Windows.psh_win_close`;
!
mapkey ..f7 @MAPKEY_NAMETol. Hriadela f7;@MAPKEY_LABELf7;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTable`  `Shaft`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTblName`  `f`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolGrade`  `7`;
!
mapkey ..g6 @MAPKEY_NAMETol. Hriadela g6;@MAPKEY_LABELg6;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTable`  `Shaft`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTblName`  `g`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolGrade`  `6`;
!
mapkey ..h6 @MAPKEY_NAMETol. Hriadela h6;@MAPKEY_LABELh6;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTable`  `Shaft`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTblName`  `h`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolGrade`  `6`;
!
mapkey ..j6 @MAPKEY_NAMETol. Hriadela j6;@MAPKEY_LABELj6;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTable`  `Shaft`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTblName`  `j`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolGrade`  `6`;
!
mapkey ..k6 @MAPKEY_NAMETol. Hriadela k6;@MAPKEY_LABELk6;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTable`  `Shaft`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTblName`  `k`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolGrade`  `6`;
!
mapkey ..m6 @MAPKEY_NAMETol. Hriadela m6;@MAPKEY_LABELm6;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTable`  `Shaft`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTblName`  `m`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolGrade`  `6`;
!
mapkey .h6 @MAPKEY_NAMETol. Diery H6;@MAPKEY_LABELH6;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTable`  `Hole`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTblName`  `H`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolGrade`  `6`;
!
mapkey .h7 @MAPKEY_NAMETol. Diery H7;@MAPKEY_LABELH7;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTable`  `Hole`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTblName`  `H`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolGrade`  `7`;
!
mapkey .h8 @MAPKEY_NAMETol. Diery H8;@MAPKEY_LABELH8;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTable`  `Hole`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTblName`  `H`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolGrade`  `8`;
!
mapkey .x45 @MAPKEY_NAMEPo oznaceni koty prida znak x45;\
mapkey(continued) @MAPKEY_LABELSuffix x45;~ Command `ProCmdPmaChkSldPnlDimTxt`  1;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;\
mapkey(continued) ~ Command `ProCmdPmaInpDimSuffix`  `x45°`;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimTxt`  0;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;
!
mapkey .fi @MAPKEY_NAMEPo oznaceni koty prida znak priemeru Priemeru;\
mapkey(continued) @MAPKEY_LABELPrefix fi;~ Command `ProCmdPmaChkSldPnlDimTxt`  1;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;\
mapkey(continued) ~ Command `ProCmdPmaInpDimPrefix`  `n`;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimTxt`  0;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;
!
mapkey .pm @MAPKEY_NAMEPo oznaceni koty prida znak M;@MAPKEY_LABELPrefix M;\
mapkey(continued) @MAPKEY_LABELPrefix fi;~ Command `ProCmdPmaChkSldPnlDimTxt`  1;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;\
mapkey(continued) ~ Command `ProCmdPmaInpDimPrefix`  `M`;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimTxt`  0;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;
!
mapkey dr1 @MAPKEY_NAMEDrsnost povrchu - Ra;@MAPKEY_LABELSym Ra;\
mapkey(continued) ~ Command `ProCmdDwgCrSymInstCust` ;~ Activate `drawing_sym` `browse`;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `DLG_PREVIEW_POST` `file_open`;\
mapkey(continued) ~ Select `file_open` `Ph_list.Filelist` 1 `drsnost_povrchu.sym`;\
mapkey(continued) ~ Activate `file_open` `Ph_list.Filelist` 1 `drsnost_povrchu.sym`;\
mapkey(continued) ~ Open `drawing_sym` `Type_OptionMenu`;\
mapkey(continued) ~ Close `drawing_sym` `Type_OptionMenu`;\
mapkey(continued) ~ Select `drawing_sym` `Type_OptionMenu` 1 `Normal to Entity`;\
mapkey(continued) ~ Select `drawing_sym` `tab_main` 1 `lay_var_text`;\
mapkey(continued) ~ FocusIn `drawing_sym` `inp_gtolchar.0`;
!
mapkey dr2 @MAPKEY_NAMEDrsnost povrchu - Ra (otocena);\
mapkey(continued) @MAPKEY_LABELSym Ra (otocena);~ Command `ProCmdDwgCrSymInstCust` ;\
mapkey(continued) ~ Activate `drawing_sym` `browse`;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `DLG_PREVIEW_POST` `file_open`;\
mapkey(continued) ~ Select `file_open` `Ph_list.Filelist` 1 `drsnost_povrchu.sym`;\
mapkey(continued) ~ Activate `file_open` `Ph_list.Filelist` 1 `drsnost_povrchu.sym`;\
mapkey(continued) ~ Open `drawing_sym` `Type_OptionMenu`;\
mapkey(continued) ~ Close `drawing_sym` `Type_OptionMenu`;\
mapkey(continued) ~ Select `drawing_sym` `Type_OptionMenu` 1 `Normal to Entity`;\
mapkey(continued) ~ Select `drawing_sym` `tab_main` 1 `lay_group`;\
mapkey(continued) ~ Expand `drawing_sym` `tree_group` `21`;\
mapkey(continued) ~ Select `drawing_sym` `tree_group` 1 `24`;\
mapkey(continued) ~ Select `drawing_sym` `tab_main` 1 `lay_var_text`;\
mapkey(continued) ~ FocusIn `drawing_sym` `inp_gtolchar.2`;
!
mapkey dr3 @MAPKEY_NAMEDrsnost povrchu - Neobrobena;\
mapkey(continued) @MAPKEY_LABELSym Neobrobena;~ Command `ProCmdDwgCrSymInstCust` ;\
mapkey(continued) ~ Activate `drawing_sym` `browse`;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `DLG_PREVIEW_POST` `file_open`;\
mapkey(continued) ~ Select `file_open` `Ph_list.Filelist` 1 `drsnost_povrchu.sym`;\
mapkey(continued) ~ Activate `file_open` `Ph_list.Filelist` 1 `drsnost_povrchu.sym`;\
mapkey(continued) ~ Open `drawing_sym` `Type_OptionMenu`;\
mapkey(continued) ~ Close `drawing_sym` `Type_OptionMenu`;\
mapkey(continued) ~ Select `drawing_sym` `Type_OptionMenu` 1 `Normal to Entity`;\
mapkey(continued) ~ Select `drawing_sym` `tab_main` 1 `lay_group`;\
mapkey(continued) ~ Select `drawing_sym` `tree_group` 1 `27`;\
mapkey(continued) ~ Select `drawing_sym` `tab_main` 1 `lay_var_text`;
!
mapkey dr4 @MAPKEY_NAMEDrsnost povrchu - Na vykres;@MAPKEY_LABELSym vykres;\
mapkey(continued) ~ Command `ProCmdDwgCrSymInstCust` ;~ Activate `drawing_sym` `browse`;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `DLG_PREVIEW_POST` `file_open`;\
mapkey(continued) ~ Select `file_open` `Ph_list.Filelist` 1 `drsnost_povrchu.sym`;\
mapkey(continued) ~ Activate `file_open` `Ph_list.Filelist` 1 `drsnost_povrchu.sym`;\
mapkey(continued) ~ Select `drawing_sym` `tab_main` 1 `lay_group`;\
mapkey(continued) ~ Select `drawing_sym` `tree_group` 1 `41`;\
mapkey(continued) ~ Select `drawing_sym` `tab_main` 1 `lay_var_text`;\
mapkey(continued) ~ FocusIn `drawing_sym` `inp_gtolchar.0`;\
mapkey(continued) ~ Open `drawing_sym` `inp_gtolchar.0`;~ Close `drawing_sym` `inp_gtolchar.0`;\
mapkey(continued) ~ Select `drawing_sym` `inp_gtolchar.0` 1 `1`;
!
mapkey eq @MAPKEY_NAMEUkoncenie programu Creo Elements/Pro;\
mapkey(continued) @MAPKEY_LABELExit-Quit;~ Command `ProCmdExit` ;\
mapkey(continued) ~ Activate `UI Message Dialog` `yes`;
!
mapkey es @MAPKEY_NAMEExit from sketch;@MAPKEY_LABELExit from sketch;\
mapkey(continued) ~ Command `ProCmdSketQuit` ;~ FocusIn `UI Message Dialog` `yes`;\
mapkey(continued) ~ Activate `UI Message Dialog` `yes`;~ Enter `main_dlg_cur` `dashInst0.Quit`;\
mapkey(continued) ~ Activate `main_dlg_cur` `dashInst0.Quit`;~ Command `ProCmdViewNamePick` 1 ;\
mapkey(continued) ~ Select `nameviewlist` `nv_list`1  `Default`;
!
mapkey pz @MAPKEY_NAMEZmení symbol pozície podlľa ISO;\
mapkey(continued) @MAPKEY_LABELBOM symbol;\
mapkey(continued) ~ Activate `main_dlg_cur` `page_Table_control_btn` 1;\
mapkey(continued) ~ Open `main_dlg_cur` `Sst_bar.filter_list`;\
mapkey(continued) ~ Close `main_dlg_cur` `Sst_bar.filter_list`;\
mapkey(continued) ~ Select `main_dlg_cur` `Sst_bar.filter_list` 1 `22_Table_SEL FILTER`;\
mapkey(continued) @MANUAL_PAUSEVyber tabulku\npotom stlac OK;\
mapkey(continued) ~ Timer `UI Desktop` `UI Desktop` `popupMenuRMBTimerCB`;\
mapkey(continued) ~ Close `rmb_popup` `PopupMenu`;~ Command `ProCmdEditProperties` ;\
mapkey(continued) ~ Select `Odui_Dlg_00` `pg_vis_tab` 1 `tab_2`;\
mapkey(continued) ~ Open `Odui_Dlg_00` `t2.opt_balloon_type`;\
mapkey(continued) ~ Trigger `Odui_Dlg_00` `t2.opt_balloon_type` `simple`;\
mapkey(continued) ~ Trigger `Odui_Dlg_00` `t2.opt_balloon_type` `quantity`;\
mapkey(continued) ~ Trigger `Odui_Dlg_00` `t2.opt_balloon_type` `custom`;\
mapkey(continued) ~ Close `Odui_Dlg_00` `t2.opt_balloon_type`;\
mapkey(continued) ~ Select `Odui_Dlg_00` `t2.opt_balloon_type` 1 `custom`;\
mapkey(continued) ~ FocusOut `Odui_Dlg_00` `t2.opt_balloon_type`;\
mapkey(continued) ~ Activate `Odui_Dlg_00` `t2.push_browse_balloon_sym`;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `DLG_PREVIEW_POST` `file_open`;\
mapkey(continued) ~ Select `file_open` `Ph_list.Filelist` 1 `index_qty.sym`;\
mapkey(continued) ~ Activate `file_open` `Ph_list.Filelist` 1 `index_qty.sym`;\
mapkey(continued) ~ Activate `Odui_Dlg_00` `stdbtn_1`;\
mapkey(continued) ~ Open `main_dlg_cur` `Sst_bar.filter_list`;\
mapkey(continued) ~ Close `main_dlg_cur` `Sst_bar.filter_list`;\
mapkey(continued) ~ Select `main_dlg_cur` `Sst_bar.filter_list` 1 `48_Table Context \
mapkey(continued) Default_SEL FILTER`;\
mapkey(continued) ~ Activate `main_dlg_cur` `user_custom_page_277822816_control_btn` 1;
!
mapkey rt @MAPKEY_NAMErectangle in sketch;@MAPKEY_LABELrectangle;\
mapkey(continued) ~ Command `ProCmdSketRectangle` 1;
!
mapkey fl @MAPKEY_NAMEfillet in sketch;@MAPKEY_LABELfillet;\
mapkey(continued) ~ Select `main_dlg_cur` `MenuBar1`1  `Sketch`;\
mapkey(continued) ~ Select `main_dlg_cur` `Sketch.cbSkecthFillet`;\
mapkey(continued) ~ Close `main_dlg_cur` `MenuBar1`;\
mapkey(continued) ~ Close `main_dlg_cur` `Sketch.cbSkecthFillet`;\
mapkey(continued) ~ Activate `main_dlg_cur` `chkCircular`1;
!
mapkey ro @MAPKEY_NAMEscale & rotate;@MAPKEY_LABELrotate;\
mapkey(continued) ~ Select `main_dlg_cur` `MenuBar1`1  `Edit`;\
mapkey(continued) ~ Close `main_dlg_cur` `MenuBar1`;\
mapkey(continued) ~ Activate `main_dlg_cur` `Edit.chkScaleRotate`1 ;\
mapkey(continued) ~ FocusIn `scalerotate` `ScaleInput`;
!
mapkey di @MAPKEY_NAMEdimension in sketch;@MAPKEY_LABELdimension;\
mapkey(continued) ~ Select `main_dlg_cur` `MenuBar1`1  `Sketch`;\
mapkey(continued) ~ Select `main_dlg_cur` `Sketch.cbDimension`;\
mapkey(continued) ~ Close `main_dlg_cur` `MenuBar1`;\
mapkey(continued) ~ Close `main_dlg_cur` `Sketch.cbDimension`;\
mapkey(continued) ~ Activate `main_dlg_cur` `chkNormal`0;
!
mapkey ac @MAPKEY_NAMEAdd component to the assembly.; @MAPKEY_LABELAssemble;\
mapeky(continued) ~ Command `ProCmdCompAssem` ;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `DLG_PREVIEW_POST` `file_open`;
!
mapkey $F2 @MAPKEY_NAMEopen/close model tree;\
mapkey(continued) @MAPKEY_LABELopen/close model tree;\
mapkey(continued) ~ Activate `main_dlg_cur` `NavigatorOpenCloseChkBtn` 0;
!
mapkey $F3 @MAPKEY_NAMEopen browser;@MAPKEY_LABELopen browser;\
mapkey(continued) ~ Activate `main_dlg_cur` `BrowserOpenCloseChkBtn` 1;
!
mapkey $F4 @MAPKEY_NAMEclose browser;@MAPKEY_LABELclose browser;\
mapkey(continued) ~ Activate `main_dlg_cur` `BrowserOpenCloseChkBtn` 0;\
mapkey(continued) ~ Timer `UI Desktop` `UI Desktop` `EmbedBrowserTimer`;
!
mapkey $F5 @MAPKEY_NAMEUpdate drw;@MAPKEY_LABELUpdate drw;\
mapkey(continued) ~ Activate `main_dlg_cur` `page_Review_control_btn` 1;\
mapkey(continued) ~ Command `ProCmdDwgRegenModel` ;#AUTOMATIC;~ Command `ProCmdDwgTblRegUpd` ;\
mapkey(continued) ~ Command `ProCmdDwgRegenDraft` ;~ Command `ProCmdDwgUpdateSheets` ;\
mapkey(continued) ~ Command `ProCmdViewRepaint` ;\
mapkey(continued) ~ Activate `main_dlg_cur` `page_View_control_btn` 1;\
mapkey(continued) ~ Command `ProCmdViewRefit` ;\
mapkey(continued) ~ Activate `main_dlg_cur` `page_Layout_control_btn` 1;
!
mapkey op @MAPKEY_NAMEopen prt;@MAPKEY_LABELopen prt;\
mapkey(continued) ~ Command `ProCmdModelOpen` ;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `DLG_PREVIEW_POST` `file_open`;\
mapkey(continued) ~ Open `file_open` `Type`;~ Close `file_open` `Type`;\
mapkey(continued) ~ Select `file_open` `Type` 1 `db_2`;\
mapkey(continued) ~ Activate `file_open` `workspace_pb`;
!
mapkey oa @MAPKEY_NAMEopen asm;@MAPKEY_LABELopen asm;\
mapkey(continued) ~ Command `ProCmdModelOpen` ;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `DLG_PREVIEW_POST` `file_open`;\
mapkey(continued) ~ Open `file_open` `Type`;~ Close `file_open` `Type`;\
mapkey(continued) ~ Select `file_open` `Type` 1 `db_1`;\
mapkey(continued) ~ Activate `file_open` `workspace_pb`;
!
mapkey od @MAPKEY_NAMEopen drw;@MAPKEY_LABELopen drw;\
mapkey(continued) ~ Command `ProCmdModelOpen` ;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `DLG_PREVIEW_POST` `file_open`;\
mapkey(continued) ~ Open `file_open` `Type`;~ Close `file_open` `Type`;\
mapkey(continued) ~ Select `file_open` `Type` 1 `db_4`;\
mapkey(continued) ~ Activate `file_open` `workspace_pb`;
!
mapkey re @MAPKEY_NAMERevolve Tool;@MAPKEY_LABELRevolve Tool;\
mapkey(continued) ~ Activate `main_dlg_cur` `page_Model_control_btn` 1;\
mapkey(continued) ~ Command `ProCmdFtRevolve`;
!
mapkey ee @MAPKEY_NAMEExtrude Tool;@MAPKEY_LABELExtrude Tool;\
mapkey(continued) ~ Activate `main_dlg_cur` `page_Model_control_btn` 1;\
mapkey(continued) ~ Command `ProCmdFtExtrude`;
!
mapkey li @MAPKEY_NAMEline in sketch;@MAPKEY_LABELline;\
mapkey(continued) ~ Command `ProCmdSketLine`  1;
!
mapkey cl @MAPKEY_NAMEcenterline in sketch;@MAPKEY_LABELcenterline;\
mapkey(continued) ~ Command `ProCmdSketCenterline`  1;
!
mapkey arc @MAPKEY_NAMEarc-3 point in sketch;@MAPKEY_LABELarc-3 point;\
mapkey(continued) ~ Command `ProCmdSket3Point`  1;
!
mapkey cc @MAPKEY_NAMEcircle in sketch;@MAPKEY_LABELcircle;\
mapkey(continued) ~ Command `ProCmdSketCenterPoint`  1;
!
mapkey spl @MAPKEY_NAMEspline in sketch;@MAPKEY_LABELspline;\
mapkey(continued) ~ Command `ProCmdSketSpline`  1;
!
mapkey mi @MAPKEY_NAMEmirror;@MAPKEY_LABELmirror;~ Command `ProCmdFtMirror`;
!
mapkey tr @MAPKEY_NAMEtrim corner;@MAPKEY_LABELtrim corner;\
mapkey(continued) ~ Command `ProCmdEditCorner`  1;
!
mapkey ok @MAPKEY_NAMEsketch>DONE;@MAPKEY_LABELsketch>DONE;\
mapkey(continued) ~ Command `ProCmdSketDone` ;~ Activate `UI Message Dialog` `yes`;
!
mapkey sw @MAPKEY_NAMEsketch view;@MAPKEY_LABELsketch view;\
mapkey(continued) ~ Command `ProCmdViewSketchView`;
!
mapkey sc @MAPKEY_NAMEv zostave oznaci rodica partu;\
mapkey(continued) @MAPKEY_LABELselect parent;\
mapkey(continued) ~ Timer `UI Desktop` `UI Desktop` `popupMenuRMBTimerCB`;\
mapkey(continued) ~ Close `rmb_popup` `PopupMenu`;~ Activate `rmb_popup` `Selobj_parent`;
!
mapkey ff @MAPKEY_NAMEchange orientation of component;@MAPKEY_LABELflip;\
mapkey(continued) ~ Activate `main_dlg_cur` `maindashInst0.constr_flip_pbtn`;
!
mapkey ss @MAPKEY_NAMEulozi vo formate pdf;@MAPKEY_LABELExport pdf;\
mapkey(continued) ~ Close `main_dlg_cur` `appl_casc`;~ Command `ProCmdModelSaveAs` ;\
mapkey(continued) ~ Open `file_saveas` `type_option`;~ Close `file_saveas` `type_option`;\
mapkey(continued) ~ Select `file_saveas` `type_option` 1 `db_617`;
!
mapkey stroj_tabulky @MAPKEY_NAMEOtvorí strojnícke tabuľky;\
mapkey(continued) @MAPKEY_LABELStrojnícke tabuľky;\
mapkey(continued) @SYSTEMstart "" /b "\\\\10.2.0.22\\mechanika\\.applic\\avsys\\resources\\strojnicke_tabulky.pdf";
!
mapkey rnm @MAPKEY_LABELRename model;~ Command `ProCmdModelRename`;
!
mapkey nd @MAPKEY_NAMECreates new drawing;@MAPKEY_LABELNew drawing;\
mapkey(continued) ~ Command `ProCmdModelNew` ;~ Select `new` `Type` 1 `Drawing`;
!
mapkey up_ash @MAPKEY_NAMEUpdates all sheets;@MAPKEY_LABELUpdate sheets;\
mapkey(continued) ~ Command `ProCmdDwgSelectAllSheets` ;~ Command `ProCmdDwgUpdateSheets`;\
mapkey(continued) ~ Activate `main_dlg_cur` `user_custom_page_277822816_control_btn` 1;
!
mapkey yy ~ Command `ProCmdViewHide`;\
mapkey(continued) ~ Command `ProCmdViewShow`;
!
mapkey hide_format @MAPKEY_NAMEHides drawing format;\
mapkey(continued) @MAPKEY_LABELHide format;~ Command `ProCmdDwgPageSetup` ;\
mapkey(continued) ~ Activate `pagesetup` `ChkShowFmt` 0;~ Activate `pagesetup` `OK`;
!
mapkey sym_rozvin @MAPKEY_NAMEVlozi symbol rozvinu;@MAPKEY_LABELSym rozvin;\
mapkey(continued) ~ Command `ProCmdDwgCrSymInstCust` ;~ Activate `drawing_sym` `browse`;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `DLG_PREVIEW_POST` `file_open`;\
mapkey(continued) ~ Select `file_open` `Ph_list.Filelist` 1 `rozvin.sym`;\
mapkey(continued) ~ Activate `file_open` `Ph_list.Filelist` 1 `rozvin.sym`;\
mapkey(continued) ~ Select `drawing_sym` `tab_main` 1 `lay_var_text`;\
mapkey(continued) ~ FocusIn `drawing_sym` `inp_gtolchar.0`;
!
mapkey sym_zakladna @MAPKEY_NAMEInsert datum reference symbol;\
mapkey(continued) @MAPKEY_LABELSym zakladna;~ Command `ProCmdDwgCrSymInstCust` ;\
mapkey(continued) ~ Activate `drawing_sym` `browse`;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `DLG_PREVIEW_POST` `file_open`;\
mapkey(continued) ~ Select `file_open` `Ph_list.Filelist` 1 `zakladna.sym`;\
mapkey(continued) ~ Activate `file_open` `Ph_list.Filelist` 1 `zakladna.sym`;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `PREVIEW_POPUP_TIMER` \
mapkey(continued) `file_open:Ph_list.Filelist:<NULL>`;
!
mapkey quick_fpat @MAPKEY_NAMERozvin simp. rep.;@MAPKEY_LABELRozvin;\
mapkey(continued) ~ Command `ProCmdSmtUnbend` ;~ Activate `main_dlg_cur` `dashInst0.Done`;\
mapkey(continued) ~ Command `ProCmdSmtBendBack` ;~ Enter `main_dlg_cur` `dashInst0.Quit`;\
mapkey(continued) ~ Exit `main_dlg_cur` `dashInst0.Quit`;\
mapkey(continued) ~ Activate `main_dlg_cur` `dashInst0.Done`;~ Command `ProCmdViewVisTool` ;\
mapkey(continued) ~ Activate `visual_dlg0` `ZoneNmCreate`;\
mapkey(continued) ~ Update `visual_dlg0` `Table_INPUT` `Rozvin`;\
mapkey(continued) ~ Activate `visual_dlg0` `Table_INPUT`;#FEATURES;\
mapkey(continued) ~ Command `ProCmdMdlTreeSearch` ;~ Select `selspecdlg0` `RuleTab` 1 `Misc`;\
mapkey(continued) ~ Select `selspecdlg0` `RuleTypes` 1 `Last Feat`;\
mapkey(continued) ~ Activate `selspecdlg0` `EvaluateBtn`;~ Activate `selspecdlg0` `ApplyBtn`;\
mapkey(continued) ~ Activate `selspecdlg0` `CancelButton`;#DONE;#DONE/RETURN;\
mapkey(continued) ~ Select `visual_dlg0` `Table` 2 `master rep` `name_column`;\
mapkey(continued) ~ RButtonArm `visual_dlg0` `Table` 2 `master rep` `name_column`;\
mapkey(continued) ~ PopupOver `visual_dlg0` `EditPanel` 1 `Table`;\
mapkey(continued) ~ Open `visual_dlg0` `EditPanel`;~ Close `visual_dlg0` `EditPanel`;\
mapkey(continued) ~ Activate `visual_dlg0` `Activate`;~ Activate `visual_dlg0` `CloseBtn`;
!
mapkey anal_dist_surf @MAPKEY_NAMEMeasure a distance with surface filter.;\
mapkey(continued) @MAPKEY_LABELDist. surf.;~ Command `ProCmdNaMeasureDistance` ;\
mapkey(continued) ~ Open `main_dlg_cur` `Sst_bar.filter_list`;\
mapkey(continued) ~ Close `main_dlg_cur` `Sst_bar.filter_list`;\
mapkey(continued) ~ Select `main_dlg_cur` `Sst_bar.filter_list` 1 `13`;\
mapkey(continued) ~ Command `ProCmdSelFilterSet` 90;
!
mapkey anal_def_dist @MAPKEY_NAMEMeasure a distance.;@MAPKEY_LABELDistance;\
mapkey(continued) ~ Command `ProCmdNaMeasureDistance` ;\
mapkey(continued) ~ Open `main_dlg_cur` `Sst_bar.filter_list`;\
mapkey(continued) ~ Close `main_dlg_cur` `Sst_bar.filter_list`;\
mapkey(continued) ~ Select `main_dlg_cur` `Sst_bar.filter_list` 1 `0`;
!
mapkey import_step @MAPKEY_NAMEImports STEP file;@MAPKEY_LABELImport STEP;\
mapkey(continued) ~ Command `ProCmdModelOpen` ;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `DLG_PREVIEW_POST` `file_open`;\
mapkey(continued) ~ Open `file_open` `Type`;~ Close `file_open` `Type`;\
mapkey(continued) ~ Select `file_open` `Type` 1 `filter_.stp,.step,`;
!
mapkey asm_lib @MAPKEY_NAMEAssemble library component;\
mapkey(continued) @MAPKEY_LABELLibrary;~ Command `ProCmdCompAssem` ;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `DLG_PREVIEW_POST` `file_open`;\
mapkey(continued) ~ Update `file_open` `opt_EMBED_BROWSER_TB_SAB_LAYOUT` \
mapkey(continued) `\\\\10.2.0.22\\mechanika\\.applic\\avsys\\library`;\
mapkey(continued) ~ Activate `file_open` `opt_EMBED_BROWSER_TB_SAB_LAYOUT`;
!
mapkey d_flip @MAPKEY_NAMEZmeni kotu priemeru;@MAPKEY_LABELFlip;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimDisp`  1;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimDisp` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimDisp` ``;\
mapkey(continued) ~ Command `ProCmdPmaPushDimFlipArrow` ;\
mapkey(continued) ~ Command `ProCmdPmaPushDimFlipArrow` ;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTextOrient`  `ISO-above-extended`;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimDisp`  0;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimDisp` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimDisp` ``;
!
mapkey comp_merge @MAPKEY_NAMECreate boolean operation.;@MAPKEY_LABELBoolean;\
mapkey(continued) ~ Command `ProCmdMmCompOper` ;#BOOLEAN OPERATIONS;
!
mapkey asm_din912 @MAPKEY_NAMEAssemble screw DIN912.;\
mapkey(continued) @MAPKEY_LABELDIN912;~ Command `ProCmdCompAssem` ;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `DLG_PREVIEW_POST` `file_open`;\
mapkey(continued) ~ Update `file_open` `opt_EMBED_BROWSER_TB_SAB_LAYOUT` \
mapkey(continued) `\\\\10.2.0.22\\mechanika\\.applic\\avsys\\library\\01_Normalizovane\\01_Skrutky`;\
mapkey(continued) ~ Activate `file_open` `opt_EMBED_BROWSER_TB_SAB_LAYOUT`;\
mapkey(continued) ~ Select `file_open` `Ph_list.Filelist` 1 `din912.prt`;\
mapkey(continued) ~ Activate `file_open` `Ph_list.Filelist` 1 `din912.prt`;\
mapkey(continued) ~ Select `open_instance` `Tab1` 1 `byparameter`;\
mapkey(continued) ~ Select `open_instance` `paramlist` 1 `column1`;
!
mapkey asm_din912_per @MAPKEY_NAMEAssemble screw DIN912 with spring washer.;\
mapkey(continued) @MAPKEY_LABELDIN912_per;~ Command `ProCmdCompAssem` ;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `DLG_PREVIEW_POST` `file_open`;\
mapkey(continued) ~ Update `file_open` `opt_EMBED_BROWSER_TB_SAB_LAYOUT` \
mapkey(continued) `\\\\10.2.0.22\\mechanika\\.applic\\avsys\\library\\01_Normalizovane\\01_Skrutky`;\
mapkey(continued) ~ Activate `file_open` `opt_EMBED_BROWSER_TB_SAB_LAYOUT`;\
mapkey(continued) ~ Select `file_open` `Ph_list.Filelist` 1 `din912_per.prt`;\
mapkey(continued) ~ Activate `file_open` `Ph_list.Filelist` 1 `din912_per.prt`;\
mapkey(continued) ~ Select `open_instance` `Tab1` 1 `byparameter`;\
mapkey(continued) ~ Select `open_instance` `paramlist` 1 `column1`;
!
mapkey asm_din7991 @MAPKEY_NAMEAssemble screw DIN7991.;\
mapkey(continued) @MAPKEY_LABELDIN7991;~ Command `ProCmdCompAssem` ;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `DLG_PREVIEW_POST` `file_open`;\
mapkey(continued) ~ Update `file_open` `opt_EMBED_BROWSER_TB_SAB_LAYOUT` \
mapkey(continued) `\\\\10.2.0.22\\mechanika\\.applic\\avsys\\library\\01_Normalizovane\\01_Skrutky`;\
mapkey(continued) ~ Activate `file_open` `opt_EMBED_BROWSER_TB_SAB_LAYOUT`;\
mapkey(continued) ~ Select `file_open` `Ph_list.Filelist` 1 `din7991.prt`;\
mapkey(continued) ~ Activate `file_open` `Ph_list.Filelist` 1 `din7991.prt`;\
mapkey(continued) ~ Select `open_instance` `Tab1` 1 `byparameter`;\
mapkey(continued) ~ Select `open_instance` `paramlist` 1 `column1`;
!
mapkey asm_din934 @MAPKEY_NAMEAssemble nut DIN934;@MAPKEY_LABELDIN934;\
mapkey(continued) ~ Command `ProCmdCompAssem` ;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `DLG_PREVIEW_POST` `file_open`;\
mapkey(continued) ~ Update `file_open` `opt_EMBED_BROWSER_TB_SAB_LAYOUT` \
mapkey(continued) `\\\\10.2.0.22\\mechanika\\.applic\\avsys\\library\\01_Normalizovane\\02_Matice`;\
mapkey(continued) ~ Activate `file_open` `opt_EMBED_BROWSER_TB_SAB_LAYOUT`;\
mapkey(continued) ~ Select `file_open` `Ph_list.Filelist` 1 `din934.prt`;\
mapkey(continued) ~ Activate `file_open` `Ph_list.Filelist` 1 `din934.prt`;\
mapkey(continued) ~ Select `open_instance` `Tab1` 1 `byparameter`;\
mapkey(continued) ~ Select `open_instance` `paramlist` 1 `column1`;
!
mapkey asm_festo_qs @MAPKEY_NAMEAssemble Festo QS part.;\
mapkey(continued) @MAPKEY_LABELFesto QS;~ Command `ProCmdCompAssem` ;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `DLG_PREVIEW_POST` `file_open`;\
mapkey(continued) ~ Update `file_open` `opt_EMBED_BROWSER_TB_SAB_LAYOUT` \
mapkey(continued) `\\\\10.2.0.22\\mechanika\\.applic\\avsys\\library\\02_Pneumatika-Festo\\01_Rychlospojky`;\
mapkey(continued) ~ Activate `file_open` `opt_EMBED_BROWSER_TB_SAB_LAYOUT`;
!
mapkey smt_thickness_sym @MAPKEY_NAMEAdd thickness note with leader.;\
mapkey(continued) @MAPKEY_LABELThickness note;~ Command `ProCmdDtlInsLeaderNote` ;\
mapkey(continued) @PAUSE_FOR_SCREEN_PICK; @PAUSE_FOR_SCREEN_PICK;\
mapkey(continued) ~ Key `main_dlg_cur` `proe_win` 9 747 329 4259937 1024 2182 1162 2560 1440 \
mapkey(continued) 354243 `A`;~ Timer `UI Desktop` `UI Desktop` `popupMenuRMBTimerCB`;\
mapkey(continued) ~ Close `rmb_popup` `PopupMenu`;\
mapkey(continued) ~ Command `ProCmdEditProperties@PopupMenuGraphicWinStack` ;\
mapkey(continued) ~ Update `note_text_asynch` `txt_note_text` `&smt_thickness[.1]mm`;\
mapkey(continued) ~ FocusOut `note_text_asynch` `txt_note_text`;\
mapkey(continued) ~ Activate `note_text_asynch` `psh_ok`;
!
mapkey grid_snap_off @MAPKEY_NAMETurn grid snappign off;@MAPKEY_LABELSnap off;\
mapkey(continued) ~ Command `ProCmdRibbonOptionsDlg` ;\
mapkey(continued) ~ Select `ribbon_options_dialog` `PageSwitcherPageList` 1 `sketch_layouts`;\
mapkey(continued) ~ Activate `ribbon_options_dialog` `sketch_layouts.grid_snap.lay_instance` 0;\
mapkey(continued) ~ Activate `ribbon_options_dialog` `OkPshBtn`;\
mapkey(continued) ~ Activate `UITools Msg Dialog Future` `no`;
!
mapkey grid_snap_on @MAPKEY_NAMETurn grid snappign on;@MAPKEY_LABELSnap on;\
mapkey(continued) ~ Command `ProCmdRibbonOptionsDlg` ;\
mapkey(continued) ~ Select `ribbon_options_dialog` `PageSwitcherPageList` 1 `sketch_layouts`;\
mapkey(continued) ~ Activate `ribbon_options_dialog` `sketch_layouts.grid_snap.lay_instance` 1;\
mapkey(continued) ~ Activate `ribbon_options_dialog` `OkPshBtn`;\
mapkey(continued) ~ Activate `UITools Msg Dialog Future` `no`;
!
mapkey grid_size_a @MAPKEY_NAMEGrid size 0.5;@MAPKEY_LABEL0.5;\
mapkey(continued) ~ Command `ProCmdDwgDraftGrid` ;#GRID PARAMS;#X&Y SPACING;\
mapkey(continued) 0.5;#DONE/RETURN;\
mapkey(continued) ~ Activate `main_dlg_cur` `user_custom_page_277822816_control_btn` 1;
!
mapkey grid_size_b @MAPKEY_NAMEGrid size 1;@MAPKEY_LABEL1;\
mapkey(continued) ~ Command `ProCmdDwgDraftGrid` ;#GRID PARAMS;#X&Y SPACING;\
mapkey(continued) 1;#DONE/RETURN;\
mapkey(continued) ~ Activate `main_dlg_cur` `user_custom_page_277822816_control_btn` 1;
!
mapkey grid_size_c @MAPKEY_NAMEGrid size 1.5;@MAPKEY_LABEL1.5;\
mapkey(continued) ~ Command `ProCmdDwgDraftGrid` ;#GRID PARAMS;#X&Y SPACING;\
mapkey(continued) 1.5;#DONE/RETURN;\
mapkey(continued) ~ Activate `main_dlg_cur` `user_custom_page_277822816_control_btn` 1;
!
mapkey grid_size_d @MAPKEY_NAMEGrid size 2;@MAPKEY_LABEL2;\
mapkey(continued) ~ Command `ProCmdDwgDraftGrid` ;#GRID PARAMS;#X&Y SPACING;\
mapkey(continued) 2;#DONE/RETURN;\
mapkey(continued) ~ Activate `main_dlg_cur` `user_custom_page_277822816_control_btn` 1;
!
mapkey grid_size_e @MAPKEY_NAMEGrid size 2.5;@MAPKEY_LABEL2.5;\
mapkey(continued) ~ Command `ProCmdDwgDraftGrid` ;#GRID PARAMS;#X&Y SPACING;\
mapkey(continued) 2.5;#DONE/RETURN;\
mapkey(continued) ~ Activate `main_dlg_cur` `user_custom_page_277822816_control_btn` 1;
!
mapkey grid_size_f @MAPKEY_NAMEGrid size 3;@MAPKEY_LABEL3;\
mapkey(continued) ~ Command `ProCmdDwgDraftGrid` ;#GRID PARAMS;#X&Y SPACING;\
mapkey(continued) 3;#DONE/RETURN;\
mapkey(continued) ~ Activate `main_dlg_cur` `user_custom_page_277822816_control_btn` 1;
!
mapkey grid_size_g @MAPKEY_NAMEGrid size 4;@MAPKEY_LABEL4;\
mapkey(continued) ~ Command `ProCmdDwgDraftGrid` ;#GRID PARAMS;#X&Y SPACING;\
mapkey(continued) 4;#DONE/RETURN;\
mapkey(continued) ~ Activate `main_dlg_cur` `user_custom_page_277822816_control_btn` 1;
!
mapkey grid_size_h @MAPKEY_NAMEGrid size 5;@MAPKEY_LABEL5;\
mapkey(continued) ~ Command `ProCmdDwgDraftGrid` ;#GRID PARAMS;#X&Y SPACING;\
mapkey(continued) 5;#DONE/RETURN;\
mapkey(continued) ~ Activate `main_dlg_cur` `user_custom_page_277822816_control_btn` 1;
!
mapkey .(2x) @MAPKEY_NAMEAdd 2x suffix.;@MAPKEY_LABEL2x;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimTxt`  1;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;\
mapkey(continued) ~ Command `ProCmdPmaInpDimSuffix`  `(2x)`;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimTxt`  0;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;
!
mapkey .(3x) @MAPKEY_NAMEAdd 3x suffix.;@MAPKEY_LABEL3x;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimTxt`  1;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;\
mapkey(continued) ~ Command `ProCmdPmaInpDimSuffix`  `(3x)`;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimTxt`  0;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;
!
mapkey .(4x) @MAPKEY_NAMEAdd 4x suffix.;@MAPKEY_LABEL4x;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimTxt`  1;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;\
mapkey(continued) ~ Command `ProCmdPmaInpDimSuffix`  `(4x)`;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimTxt`  0;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;
!
mapkey .(5x) @MAPKEY_NAMEAdd 5x suffix.;@MAPKEY_LABEL5x;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimTxt`  1;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;\
mapkey(continued) ~ Command `ProCmdPmaInpDimSuffix`  `(5x)`;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimTxt`  0;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;
!
mapkey .(6x) @MAPKEY_NAMEAdd 6x suffix.;@MAPKEY_LABEL6x;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimTxt`  1;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;\
mapkey(continued) ~ Command `ProCmdPmaInpDimSuffix`  `(6x)`;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimTxt`  0;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;
!
mapkey .(8x) @MAPKEY_NAMEAdd 8x suffix.;@MAPKEY_LABEL8x;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimTxt`  1;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;\
mapkey(continued) ~ Command `ProCmdPmaInpDimSuffix`  `(8x)`;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimTxt`  0;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;
!
mapkey .(10x) @MAPKEY_NAMEAdd 10x suffix.;@MAPKEY_LABEL10x;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimTxt`  1;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;\
mapkey(continued) ~ Command `ProCmdPmaInpDimSuffix`  `(10x)`;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimTxt`  0;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;
!
mapkey .(12x) @MAPKEY_NAMEAdd 12x suffix.;@MAPKEY_LABEL12x;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimTxt`  1;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;\
mapkey(continued) ~ Command `ProCmdPmaInpDimSuffix`  `(12x)`;\
mapkey(continued) ~ Command `ProCmdPmaChkSldPnlDimTxt`  0;\
mapkey(continued) ~ Trail `main_dlg_cur` `Dimension_Properties:ProCmdPmaChkSldPnlDimTxt` \
mapkey(continued) `RibbonPanelsOnActivate_ProCmdPmaChkSldPnlDimTxt` ``;
!
mapkey drw_rozvin @MAPKEY_NAMEVlozi rozvin;@MAPKEY_LABELRozvin;\
mapkey(continued) ~ Command `ProCmdDwgViewGen` ;~ Activate `sel_presentation` `ok_psh`;\
mapkey(continued) @PAUSE_FOR_SCREEN_PICK;~ Activate `drawing_view` `psh_ok`;\
mapkey(continued) ~ Timer `UI Desktop` `UI Desktop` `popupMenuRMBTimerCB`;\
mapkey(continued) ~ Close `rmb_popup` `PopupMenu`;\
mapkey(continued) ~ Trail `UI Desktop` `UI Desktop` `UIT_TRANSLUCENT` `NEED_TO_CLOSE`;\
mapkey(continued) ~ Command `ProCmdDwgReplaceViewModel@PopupMenuGraphicWinStack` ;\
mapkey(continued) ~ Activate `gen_repl_dlg` `PB_NewComp`;\
mapkey(continued) ~ Select `mdlbrowser` `AssyTree` 1 `node0:ROZVIN`;\
mapkey(continued) ~ Activate `mdlbrowser` `OK`;~ Activate `gen_repl_dlg` `DoneBtn`;
!
mapkey tol_+-0.01 @MAPKEY_NAMEAdd tolerance;@MAPKEY_LABEL0.01;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolMode`  `Symmetric`;\
mapkey(continued) ~ Open `main_dlg_cur` \
mapkey(continued) `Dimension_Properties:ProCmdPmaOptDimTolTable:ProCmdPmaOptDimTolTable`;\
mapkey(continued) ~ Trigger `main_dlg_cur` \
mapkey(continued) `Dimension_Properties:ProCmdPmaOptDimTolTable:ProCmdPmaOptDimTolTable` \
mapkey(continued) `None`;\
mapkey(continued) ~ Close `main_dlg_cur` \
mapkey(continued) `Dimension_Properties:ProCmdPmaOptDimTolTable:ProCmdPmaOptDimTolTable`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTable`  `None`;\
mapkey(continued) ~ Command `ProCmdPmaInpDimUpperTol`  `0.01`;
!
mapkey tol_+-0.02 @MAPKEY_NAMEAdd tolerance;@MAPKEY_LABEL0.02;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolMode`  `Symmetric`;\
mapkey(continued) ~ Open `main_dlg_cur` \
mapkey(continued) `Dimension_Properties:ProCmdPmaOptDimTolTable:ProCmdPmaOptDimTolTable`;\
mapkey(continued) ~ Trigger `main_dlg_cur` \
mapkey(continued) `Dimension_Properties:ProCmdPmaOptDimTolTable:ProCmdPmaOptDimTolTable` \
mapkey(continued) `None`;\
mapkey(continued) ~ Close `main_dlg_cur` \
mapkey(continued) `Dimension_Properties:ProCmdPmaOptDimTolTable:ProCmdPmaOptDimTolTable`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTable`  `None`;\
mapkey(continued) ~ Command `ProCmdPmaInpDimUpperTol`  `0.02`;
!
mapkey tol_+-0.05 @MAPKEY_NAMEAdd tolerance;@MAPKEY_LABEL0.05;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolMode`  `Symmetric`;\
mapkey(continued) ~ Open `main_dlg_cur` \
mapkey(continued) `Dimension_Properties:ProCmdPmaOptDimTolTable:ProCmdPmaOptDimTolTable`;\
mapkey(continued) ~ Trigger `main_dlg_cur` \
mapkey(continued) `Dimension_Properties:ProCmdPmaOptDimTolTable:ProCmdPmaOptDimTolTable` \
mapkey(continued) `None`;\
mapkey(continued) ~ Close `main_dlg_cur` \
mapkey(continued) `Dimension_Properties:ProCmdPmaOptDimTolTable:ProCmdPmaOptDimTolTable`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTable`  `None`;\
mapkey(continued) ~ Command `ProCmdPmaInpDimUpperTol`  `0.05`;
!
mapkey tol_+-0.1 @MAPKEY_NAMEAdd tolerance;@MAPKEY_LABEL0.1;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolMode`  `Symmetric`;\
mapkey(continued) ~ Open `main_dlg_cur` \
mapkey(continued) `Dimension_Properties:ProCmdPmaOptDimTolTable:ProCmdPmaOptDimTolTable`;\
mapkey(continued) ~ Trigger `main_dlg_cur` \
mapkey(continued) `Dimension_Properties:ProCmdPmaOptDimTolTable:ProCmdPmaOptDimTolTable` \
mapkey(continued) `None`;\
mapkey(continued) ~ Close `main_dlg_cur` \
mapkey(continued) `Dimension_Properties:ProCmdPmaOptDimTolTable:ProCmdPmaOptDimTolTable`;\
mapkey(continued) ~ Command `ProCmdPmaOptDimTolTable`  `None`;\
mapkey(continued) ~ Command `ProCmdPmaInpDimUpperTol`  `0.1`;

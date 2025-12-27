package controller;

import model.Model;
import model.entity.Address;
import model.entity.Orders;
import model.entity.Product;
import model.entity.User;
import model.entity.enums.*;
import model.service.SessionService;
import util.DBconection;
import util.Utilities;
import view.DashboardView;
import view.LoginView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.List;

public class DashboardController implements ActionListener, ListSelectionListener, TableModelListener, ItemListener {

    private DashboardView dashboardView;
    private Model model;
    private Product currentProduct;
    private Orders lastOrder;
    private Orders sales;

    public DashboardController(DashboardView dashboardView, Model model) {
        this.dashboardView = dashboardView;
        this.model = model;

        initLogin();
        addActionListener(this);
        addListSelectionListener(this);
        addTableModelListener(this);
        addItemListener(this);
        centerAllTables();
    }

    private void addTableModelListener(TableModelListener e) {
        dashboardView.dtmTableProducts.addTableModelListener(e);
        dashboardView.dtmTablePreviewADInfo.addTableModelListener(e);
        dashboardView.dtmTablePreviewNormalInfo.addTableModelListener(e);
        dashboardView.dtmtableAdminSales.addTableModelListener(e);
    }

    private void addItemListener(ItemListener e) {
        dashboardView.cbEditProfileCountry.addItemListener(e);
    }


    private void addListSelectionListener(ListSelectionListener e) {

        dashboardView.tableAdminProducts.setCellSelectionEnabled(true);
        ListSelectionModel tableAdminProducts = dashboardView.tableAdminProducts.getSelectionModel();
        tableAdminProducts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableAdminProducts.addListSelectionListener(e);

        dashboardView.tableBags.setCellSelectionEnabled(true);
        ListSelectionModel tableBags = dashboardView.tableBags.getSelectionModel();
        tableBags.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableBags.addListSelectionListener(e);

        dashboardView.tableHistory.setCellSelectionEnabled(true);
        ListSelectionModel tableHistory = dashboardView.tableHistory.getSelectionModel();
        tableHistory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableHistory.addListSelectionListener(e);

        dashboardView.tablePreviewADInfo.setCellSelectionEnabled(true);
        ListSelectionModel tablePreviewADInfo = dashboardView.tablePreviewADInfo.getSelectionModel();
        tablePreviewADInfo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePreviewADInfo.addListSelectionListener(e);
    }

    private void addActionListener(ActionListener e) {

        dashboardView.btnHome.addActionListener(e);
        dashboardView.btnBags.addActionListener(e);
        dashboardView.btnHistory.addActionListener(e);
        dashboardView.btnProfile.addActionListener(e);
        dashboardView.btnSelling.addActionListener(e);
        dashboardView.btnAddProduct.addActionListener(e);
        dashboardView.btnConfig.addActionListener(e);
        dashboardView.btnHomeProfile.addActionListener(e);

        dashboardView.btnHomePreviewBack.addActionListener(e);
        dashboardView.btnGeneralViewInfo.addActionListener(e);
        dashboardView.btnAddressViewInfo.addActionListener(e);
        dashboardView.btnPreviewADBack.addActionListener(e);
        dashboardView.btnPreviewNormalBack.addActionListener(e);
        dashboardView.btnPreviewADHome.addActionListener(e);
        dashboardView.btnPreviewNormalHome.addActionListener(e);
        dashboardView.btnPreviewADDelete.addActionListener(e);
        dashboardView.btnPreviewNormalDelete.addActionListener(e);
        dashboardView.btnPreviewADForm.addActionListener(e);

        dashboardView.btnBagsToHome.addActionListener(e);
        dashboardView.btnBagsBuy.addActionListener(e);

        dashboardView.btnProfileHome.addActionListener(e);
        dashboardView.btnEditProfileAddressBack.addActionListener(e);
        dashboardView.btnEditProfileAddressSave.addActionListener(e);
        dashboardView.btnEditProfileAddressClean.addActionListener(e);

        dashboardView.btnProfileEdit.addActionListener(e);
        dashboardView.btnEditProfileBack.addActionListener(e);
        dashboardView.btnEditProfileSave.addActionListener(e);
        dashboardView.btnEditProfileClean.addActionListener(e);

        dashboardView.btnAddHome.addActionListener(e);
        dashboardView.btnAddSave.addActionListener(e);
        dashboardView.btnAddClean.addActionListener(e);

        dashboardView.btnConfigHome.addActionListener(e);
        dashboardView.btnConfigSave.addActionListener(e);
        dashboardView.btnConfigClean.addActionListener(e);

        dashboardView.btnHomeAdminNewProduct.addActionListener(e);
        dashboardView.btnHomeAdminUpdateForm.addActionListener(e);
        dashboardView.btnHomeAdminDelete.addActionListener(e);

        dashboardView.cbAddType.addActionListener(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String evt = e.getActionCommand();
        switch (evt) {
            case "btnHome":
            case "btnProfileHome":
            case "btnBagsToHome":
            case "btnPreviewADHome":
            case "btnPreviewNormalHome":
                showHome();
                break;
            case "btnHistory":
                Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "userHistory");
                reloadHistoryUser();
                break;
            case "btnBags":
            case "btnHomePreviewBack":
                Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "userBags");
                break;
            case "btnProfile":
            case "btnEditProfileBack":
            case "btnEditProfileAddressBack":
            case "btnPreviewADBack":
            case "btnPreviewNormalBack":

                cleanEditProfileAddressClean();
                cleanEditProfile();

                reloadAddressTable();
                reloadUserInfo();

                initHomeUser();
                initNormalInfoUser();

                Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "userViewProfile");
                break;
            case "btnEditProfileSave":
                editProfileInfo();
                break;
            case "btnEditProfileAddressSave":
                editProfileAddressInfo();
                break;
            case "btnSelling":

                cleanCurrentProduct((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "adminAddProduct");

                if (currentProduct == null) {
                    Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "adminSales");
                    reloadTableSales();
                }

                break;
            case "btnAddProduct":
            case "btnHomeAdminNewProduct":
                Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "adminAddProduct");
                break;
            case "btnConfig":

                cleanCurrentProduct((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "adminAddProduct");

                if (currentProduct == null) {
                    Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "adminConfig");
                }
                break;
            case "btnProfileEdit":
            case "btnPreviewADForm":
                Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "userEditProfile");
                break;
            case "btnEditProfileClean":
                cleanEditProfile();
                break;
            case "btnEditProfileAddressClean":
                cleanEditProfileAddressClean();
                break;
            case "btnAddHome":

                break;
            case "btnAddSave":
                saveProduct();
                break;
            case "btnAddClean":
                cleanFormProduct();
                break;
            case "btnBagsBuy":
                buyProduct();
                break;
            case "btnConfigHome":

                break;
            case "btnConfigSave":

                break;
            case "btnConfigClean":

                break;
            case "btnUserPopup":

                break;
            case "TypeOfProductCombo":
                optionAddProduct();
                break;
            case "btnHomeAdminUpdateForm":
                UpdateFromForm();
                break;
            case "btnHomeAdminDelete":
                deleteProduct();
                break;
            case "btnGeneralViewInfo":
                Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "PreviewPersonalInfo");
                reloadUserInfo();
                break;
            case "btnAddressViewInfo":
                Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "PreviewAddressInfo");
                reloadAddressTable();
                break;
            case "btnPreviewNormalDelete":
                deleteUser();
                System.out.println("Borro Perfil Usuario");
                reloadUserInfo();
                break;
            case "btnPreviewADDelete":
                deleteAddress();
                System.out.println("Borro Datos Direccion");
                reloadAddressTable();
                break;
        }
    }

    private void deleteUser() {

        User user = model.findUserById(SessionService.getCurrentUser().getId());

        if (user != null) {
            int res = Utilities.confirmMessage("Are you sure that you want to delete your Account?", "Warning");
            if (res == JOptionPane.OK_OPTION) {
                model.deleteUser(user);
                SessionService.logout();
                dashboardView.dispose();
                new LoginController(new LoginView(), model);
            }
        }


    }

    //INFO GLOBAL
    private void initLogin() {
        dashboardView.btnUserPopup.setText(SessionService.getCurrentUser().getUserName());
        showHome();
    }

    private void showHome() {
        String rolName = SessionService.getCurrentUser().getRoles().getRolName();
        CardLayout cl = (CardLayout) dashboardView.JPanelCard.getLayout();

        switch (rolName) {
            case "CLIENT":
                Utilities.displayCard(cl, dashboardView.JPanelCard, "userHome");
                Utilities.manageBtn(dashboardView.btnSelling, false);
                Utilities.manageBtn(dashboardView.btnAddProduct, false);
                Utilities.manageBtn(dashboardView.btnConfig, false);
                reloadProductsUserActive();
                reloadHistoryUser();
                reloadAddressTable();
                reloadUserInfo();
                initHomeUser();
                initNormalInfoUser();
                reloadTableSales();
                break;
            case "ADMIN":

                cleanCurrentProduct(cl, dashboardView.JPanelCard, "adminAddProduct");

                if (currentProduct == null) {
                    Utilities.displayCard(cl, dashboardView.JPanelCard, "adminHomeAdmin");
                    initFormAddProduct();
                }

                Utilities.manageBtn(dashboardView.btnHistory, false);
                Utilities.manageBtn(dashboardView.btnBags, false);
                Utilities.manageBtn(dashboardView.btnProfile, false);
                reloadProductsActive();
                break;
            default:
                System.out.println("Rol desconocido: " + rolName);
                break;
        }
    }

    private void cleanCurrentProduct(CardLayout cardLayout, JPanel jPanel, String nameCard) {


        if (currentProduct != null) {
            int res = Utilities.confirmMessage("You're updating a product right now, Are you sure you want to go home?", "Warning");
            if (res == JOptionPane.OK_OPTION) {
                cleanFormProduct();
            } else {
                Utilities.displayCard(cardLayout, jPanel, nameCard);
            }
        }

    }


    private void reloadProductsActive() {
//        dashboardView.dtmTableProducts.removeTableModelListener(this);
        List<Product> products = model.getAllProductActive();

        String[] comlums = {"ID", "Code", "Price", "Material", "Type", "Registered", "Size", "Brand", "waterproof", "Weight", "Gadget", "Security", "Wheels, status"};
        dashboardView.dtmTableProducts.setRowCount(0);
        dashboardView.dtmTableProducts.setColumnIdentifiers(comlums);

        for (Product product : products) {
            dashboardView.dtmTableProducts.addRow(new Object[]{
                    product.getIdProduct(),
                    product.getCode(),
                    product.getPrice(),
                    product.getMaterial(),
                    product.getTypeProduct(),
                    product.getRegisterDay(),
                    product.getSize(),
                    product.getBrand(),
                    product.isWaterproof(),
                    product.getWeight(),
                    product.getGadget(),
                    product.getSecurity(),
                    product.isWheels(),
                    product.getStatus()
            });

            centerTable(dashboardView.tableAdminProducts);
        }
    }


    private void reloadProductsUserActive() {
//        dashboardView.dtmTableHome.removeTableModelListener(this);

        List<Product> products = model.getAllProductActive();

        String[] comlums = {"ID", "Code", "Price", "Material", "Type", "Size", "Brand", "waterproof", "Weight", "Gadget", "Security", "Wheels"};
        dashboardView.dtmTableHome.setRowCount(0);
        dashboardView.dtmTableHome.setColumnIdentifiers(comlums);

        for (Product product : products) {
            dashboardView.dtmTableHome.addRow(new Object[]{
                    product.getIdProduct(),
                    product.getCode(),
                    product.getPrice(),
                    product.getMaterial(),
                    product.getTypeProduct(),
                    product.getSize(),
                    product.getBrand(),
                    product.isWaterproof(),
                    product.getWeight(),
                    product.getGadget(),
                    product.getSecurity(),
                    product.isWheels()
            });
        }
        centerTable(dashboardView.tableHome);
    }

    private void reloadHistoryUser() {
//        dashboardView.dtmTableHistory.removeTableModelListener(this);

        List<Orders> orders = model.findAllOrdersByUser(SessionService.getCurrentUser());
        int lastIndex = orders.size() - 1;

        if (lastIndex != -1) {
            lastOrder = orders.get(lastIndex);
        }

        String[] comlums = {"ID", "Type", "Material", "Brand", "Code", "Price"};
        dashboardView.dtmTableHistory.setRowCount(0);
        dashboardView.dtmTableHistory.setColumnIdentifiers(comlums);

        for (Orders order : orders) {
            dashboardView.dtmTableHistory.addRow(new Object[]{
                    order.getIdOrder(),
                    order.getProduct().getTypeProduct(),
                    order.getProduct().getMaterial(),
                    order.getProduct().getMaterial(),
                    order.getProduct().getCode(),
                    order.getProduct().getPrice()

            });
        }

        centerTable(dashboardView.tableHistory);
    }

    private void reloadTableSales() {
//        dashboardView.dtmTableHistory.removeTableModelListener(this);

        List<Orders> orders = model.findAllPaidOrders();
        System.out.println(orders.size() + " SIZE LIST ORDER");
        int lastIndex = orders.size() - 1;

        if (lastIndex != -1) {
            sales = orders.get(lastIndex);
        }

        String[] comlums = {"ID Order", "User Name", "User Lastname", "User Email", "Addres Country", "Addres City", "Addres Street", "Addres Apartament", "Prod Price", "Prod Type", "Prod Material"};
        dashboardView.dtmtableAdminSales.setRowCount(0);
        dashboardView.dtmtableAdminSales.setColumnIdentifiers(comlums);

        for (Orders order : orders) {
            dashboardView.dtmtableAdminSales.addRow(new Object[]{
                    order.getIdOrder(),
                    order.getUser().getName(),
                    order.getUser().getLastName(),
                    order.getUser().getEmail(),
                    order.getUser().getAddress().getCountry(),
                    order.getUser().getAddress().getCity(),
                    order.getUser().getAddress().getStreet(),
                    order.getUser().getAddress().getApartarment(),
                    order.getProduct().getCode(),
                    order.getProduct().getPrice(),
                    order.getProduct().getTypeProduct(),
                    order.getProduct().getMaterial()
            });
        }

        centerTable(dashboardView.tableAdminSales);
    }


    private void reloadUserInfo() {

        User user = SessionService.getCurrentUser();

        if (user == null) {
            return;
        }

        String[] comlums = {"iduser", "name", "lastname", "username", "email", "birthday", "balance"};
        dashboardView.dtmTablePreviewNormalInfo.setRowCount(0);
        dashboardView.dtmTablePreviewNormalInfo.setColumnIdentifiers(comlums);

        dashboardView.dtmTablePreviewNormalInfo.addRow(new Object[]{
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getUserName(),
                user.getEmail(),
                user.getBirthday(),
                user.getBalance()
        });


        centerTable(dashboardView.tablePreviewNormalInfo);
    }

    private void reloadAddressTable() {
//        dashboardView.dtmTablePreviewADInfo.removeTableModelListener(this);

        System.out.println("Cargando tabla Address");

        Address address = model.findAddresByUserId(SessionService.getCurrentUser());
        User currectUser = SessionService.getCurrentUser();
        currectUser.setAddress(address);

        String[] comlums = {"ID", "Country", "City", "Street", "Apartarment"};
        dashboardView.dtmTablePreviewADInfo.setRowCount(0);
        dashboardView.dtmTablePreviewADInfo.setColumnIdentifiers(comlums);

        if (currectUser.getAddress() != null) {
            dashboardView.dtmTablePreviewADInfo.addRow(new Object[]{
                    currectUser.getAddress().getIdAddress(),
                    currectUser.getAddress().getCountry(),
                    currectUser.getAddress().getCity(),
                    currectUser.getAddress().getStreet(),
                    currectUser.getAddress().getApartarment()
            });
        }
        centerTable(dashboardView.tablePreviewADInfo);
    }

    //NORMAL USER

    private void initNormalInfoUser() {
        User user = model.findUserById(SessionService.getCurrentUser().getId());
        dashboardView.txtProfileName.setText(user.getName());
        dashboardView.txtProfileEmail.setText(user.getEmail());
        dashboardView.txtProfileLastName.setText(user.getLastName());
        dashboardView.txtProfileUsername.setText(user.getUserName());
        dashboardView.dpProfileUsername.setDate(user.getBirthday());
    }

    private void initHomeUser() {

        Address address = model.findAddresByUserId(SessionService.getCurrentUser());
        User user = model.findUserById(SessionService.getCurrentUser().getId());


        if (address == null) {
            System.out.println("User Addres es null");
            dashboardView.lblHomeCountry.setText("UNDEFINED");
            dashboardView.lblHomeStreet.setText("UNDEFINED");
            dashboardView.lblHomeCity.setText("UNDEFINED");
            dashboardView.lblHomeNumberApartament.setText("UNDEFINED");

            dashboardView.txtProfileCountry.setText("UNDEFINED");
            dashboardView.txtProfileCity.setText("UNDEFINED");
            dashboardView.txtProfileStreet.setText("UNDEFINED");
            dashboardView.txtProfileApartament.setText("UNDEFINED");
        } else {
            user.setAddress(address);
            System.out.println("User Addres no es null");
            String country = user.getAddress().getCountry();
            String street = user.getAddress().getStreet();
            String city = user.getAddress().getCity();
            String apartament = user.getAddress().getApartarment();

            dashboardView.lblHomeCountry.setText(country);
            dashboardView.lblHomeStreet.setText(street);
            dashboardView.lblHomeCity.setText(city);
            dashboardView.lblHomeNumberApartament.setText(apartament);

            dashboardView.txtProfileCountry.setText(country);
            dashboardView.txtProfileCity.setText(city);
            dashboardView.txtProfileStreet.setText(street);
            dashboardView.txtProfileApartament.setText(apartament);
        }


        if (lastOrder == null || lastOrder.getProduct() == null) {
            dashboardView.lblHomeTypoProduct.setText("UNDEFINED");
            dashboardView.lblHomeBrand.setText("UNDEFINED");
            dashboardView.lblHomeMaterial.setText("UNDEFINED");
            dashboardView.lblHomeCodeBag.setText("UNDEFINED");
        } else {
            dashboardView.lblHomeTypoProduct.setText(lastOrder.getProduct().getTypeProduct());
            dashboardView.lblHomeBrand.setText(lastOrder.getProduct().getBrand());
            dashboardView.lblHomeMaterial.setText(lastOrder.getProduct().getMaterial());
            dashboardView.lblHomeCodeBag.setText(lastOrder.getProduct().getCode());
        }
    }

    private void editProfileInfo() {

        String nameUser = dashboardView.txtEditProfileName.getText();
        String lastNameUser = dashboardView.txtEditProfileLastName.getText();
        String userUsername = dashboardView.txtEditProfileUsername.getText();
        String passwordUser = new String(dashboardView.txtEditProfilePassword.getPassword());
        String passwordConfirmUser = new String(dashboardView.txtEditProfileConfirmPassword.getPassword());
        LocalDate birthday = dashboardView.dpEditProfileBithday.getDate();


        if (passwordUser.isEmpty() && passwordConfirmUser.isEmpty()) {
            passwordUser = SessionService.getCurrentUser().getPassword();
            System.out.println(passwordUser);
        } else {
            if (!passwordUser.equals(passwordConfirmUser)) {
                System.out.println(passwordUser + "hola");
                dashboardView.txtEditProfilePassword.requestFocus();
                Utilities.showErrorAlert("The password must match the password confirmation.");
                return;
            }
        }

        if (nameUser.isEmpty()) {
            nameUser = SessionService.getCurrentUser().getUserName();
        } else {
            SessionService.getCurrentUser().setName(nameUser);
        }

        if (lastNameUser.isEmpty()) {
            lastNameUser = SessionService.getCurrentUser().getLastName();
        } else {
            SessionService.getCurrentUser().setLastName(lastNameUser);
        }

        if (userUsername.isEmpty()) {
            userUsername = SessionService.getCurrentUser().getUserName();
        } else {
            SessionService.getCurrentUser().setUserName(userUsername);
        }

        if (birthday == null) {
            birthday = SessionService.getCurrentUser().getBirthday();
        } else {
            SessionService.getCurrentUser().setBirthday(birthday);
        }

        model.updateUser(SessionService.getCurrentUser());
        initHomeUser();
        cleanEditProfile();
        Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "userViewProfile");
    }

    private void editProfileAddressInfo() {

        Address address = model.findAddresByUserId(SessionService.getCurrentUser());
        User currectUser = SessionService.getCurrentUser();
        Object countryUserObject = dashboardView.cbEditProfileCountry.getSelectedItem();
        Object cityUserObject = dashboardView.cbEditProfileCity.getSelectedItem();
        String streetUser = dashboardView.txtEditProfileStreet.getText();
        String apartamentUser = dashboardView.txtEditProfileApartament.getText();


        if (!checkFormEditAddressUser(countryUserObject, cityUserObject, streetUser, apartamentUser)) {
            return;
        }

        String countryUser = countryUserObject.toString();
        String cityUser = cityUserObject.toString();

        System.out.println("Saving address: " + countryUser + ", " + cityUser + ", " + streetUser + ", " + apartamentUser);


        if (address != null) {

            address.setCountry(countryUser);
            address.setCity(cityUser);
            address.setStreet(streetUser);
            address.setApartarment(apartamentUser);
            currectUser.setAddress(address);
            model.updateAddress(currectUser);
        } else {
            Address addressNew = new Address(countryUser, cityUser, streetUser, apartamentUser);
            currectUser.setAddress(addressNew);
            model.saveAddress(currectUser);
        }

        initHomeUser();
        cleanEditProfileAddressClean();
        Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "userViewProfile");
    }


    private boolean checkFormEditAddressUser(Object countryUser, Object cityUser, String streetUser, String apartamentUser) {

        if (countryUser == null) {
            dashboardView.cbEditProfileCountry.requestFocus();
            Utilities.showErrorAlert("Please fill the fild Country");
            return false;
        }

        if (cityUser == null) {
            dashboardView.cbEditProfileCity.requestFocus();
            Utilities.showErrorAlert("Please fill the fild city");
            return false;
        }

        if (streetUser.isEmpty()) {
            dashboardView.txtEditProfileStreet.requestFocus();
            Utilities.showErrorAlert("Please fill the fild Street");
            return false;
        }

        if (apartamentUser.isEmpty()) {
            dashboardView.txtEditProfileApartament.requestFocus();
            Utilities.showErrorAlert("Please fill the fild Apartament");
            return false;
        }

        return true;
    }

    private void cleanEditProfile() {
        dashboardView.txtEditProfileName.setText("");
        dashboardView.txtEditProfileUsername.setText("");
        dashboardView.txtEditProfilePassword.setText("");
        dashboardView.txtEditProfileConfirmPassword.setText("");
        dashboardView.dpEditProfileBithday.setDate(LocalDate.now());
        dashboardView.cbEditProfileCountry.setSelectedIndex(-1);
        dashboardView.cbEditProfileCity.setSelectedIndex(-1);
    }

    private void cleanEditProfileAddressClean() {
        dashboardView.cbEditProfileCountry.setSelectedIndex(-1);
        dashboardView.cbEditProfileCity.setModel(new DefaultComboBoxModel<>(new String[]{"SELECT FIRST A COUNTRY"}));
        dashboardView.cbEditProfileCity.setSelectedIndex(-1);
        dashboardView.txtEditProfileStreet.setText("");
        dashboardView.txtEditProfileApartament.setText("");
    }


    //ADMIN MANAGER

    private void initFormAddProduct() {
        cleanFormProduct();
        dashboardView.dpAddDate.setEnabled(false);
        dashboardView.spAddPrice.setEnabled(false);
        dashboardView.cbAddMaterial.setEnabled(false);
        dashboardView.cbAddSize.setEnabled(false);
        dashboardView.cbAddBrand.setEnabled(false);
        dashboardView.slAddWeight.setEnabled(false);
        dashboardView.rbAddYesWater.setEnabled(false);
        dashboardView.rbAddNoWater.setEnabled(false);
        dashboardView.lblNewTypeProduct.setVisible(false);
        dashboardView.JPanelComponent.setVisible(false);
    }

    private void optionAddProduct() {

        Object selected = dashboardView.cbAddType.getSelectedItem();

        if (selected == null) {
            return;
        }

        String typeProduct = selected.toString();

        switch (typeProduct) {
            case "SUITCASE":
                ShowSuitcaseComponent(typeProduct);
                break;
            case "BAG":
                ShowSuitCaseComponent(typeProduct);
                break;
            case "TRAVELBAG":
                ShowSuitCaseComponent(typeProduct);
                break;

        }


    }

    private void ShowSuitcaseComponent(String typeProduct) {
        //SUITCASE
        dashboardView.lblNewTypeProduct.setVisible(true);
        dashboardView.lblNewTypeProduct.setText(typeProduct);
        dashboardView.cbAddSecurity.setEnabled(true);
        dashboardView.cbAddSecurity.setVisible(true);
        dashboardView.rbAddYesWheels.setEnabled(true);
        dashboardView.rbAddYesWheels.setVisible(true);
        dashboardView.rbAddNoWheels.setEnabled(true);
        dashboardView.rbAddNoWheels.setVisible(true);
        dashboardView.lblAddSecurity.setVisible(true);
        dashboardView.lblAddWheels.setVisible(true);

        //Bag or travelbag component
        dashboardView.cbAddGadget.setEnabled(false);
        dashboardView.cbAddGadget.setVisible(false);
        dashboardView.lblAddgadget.setVisible(false);

        //Normal component
        enableNormalComponentForm();
    }

    private void ShowSuitCaseComponent(String typeProduct) {
        dashboardView.lblNewTypeProduct.setVisible(true);
        dashboardView.lblNewTypeProduct.setText(typeProduct);
        dashboardView.cbAddGadget.setEnabled(true);
        dashboardView.cbAddGadget.setVisible(true);
        dashboardView.lblAddgadget.setVisible(true);

        //TRAVELBAG
        dashboardView.cbAddSecurity.setEnabled(false);
        dashboardView.cbAddSecurity.setVisible(false);
        dashboardView.rbAddYesWheels.setEnabled(false);
        dashboardView.rbAddYesWheels.setVisible(false);
        dashboardView.rbAddNoWheels.setEnabled(false);
        dashboardView.rbAddNoWheels.setVisible(false);
        dashboardView.lblAddSecurity.setVisible(false);
        dashboardView.lblAddWheels.setVisible(false);

        //Normal component
        enableNormalComponentForm();
    }

    private void enableNormalComponentForm() {
        dashboardView.dpAddDate.setEnabled(true);
        dashboardView.spAddPrice.setEnabled(true);
        dashboardView.cbAddMaterial.setEnabled(true);
        dashboardView.cbAddSize.setEnabled(true);
        dashboardView.cbAddBrand.setEnabled(true);
        dashboardView.slAddWeight.setEnabled(true);
        dashboardView.rbAddYesWater.setEnabled(true);
        dashboardView.rbAddNoWater.setEnabled(true);
        dashboardView.JPanelComponent.setVisible(true);
    }

    private void saveProduct() {

        String code = dashboardView.txtAddCode.getText();

        Object typeProduct = dashboardView.cbAddType.getSelectedItem();

        LocalDate dateRegister = dashboardView.dpAddDate.getDate();

        Object price = dashboardView.spAddPrice.getValue();

        Object material = dashboardView.cbAddMaterial.getSelectedItem();

        Object size = dashboardView.cbAddSize.getSelectedItem();

        Object brand = dashboardView.cbAddBrand.getSelectedItem();

        int weight = dashboardView.slAddWeight.getValue();

        boolean waterproofYes = dashboardView.rbAddYesWater.isSelected();

        boolean waterproofNo = dashboardView.rbAddNoWater.isSelected();

        Object gadget = dashboardView.cbAddGadget.getSelectedItem();

        Object security = dashboardView.cbAddSecurity.getSelectedItem();

        boolean wheelsYes = dashboardView.rbAddYesWheels.isSelected();

        boolean wheelsNo = dashboardView.rbAddNoWheels.isSelected();


        if (!checkValuesFormNormal(typeProduct, dateRegister, price, material, size, brand, weight, waterproofYes, waterproofNo, gadget, code)) {
            return;
        }

        double priceP = ((Number) price).doubleValue();
        String typeProductS = typeProduct.toString();
        String materialS = material.toString();
        String sizeS = size.toString();
        String brandS = brand.toString();
        String gadgetS = gadget.toString();

        Product product = null;
        if (currentProduct == null) {
            switch (typeProductS) {
                case "TRAVELBAG":
                case "BAG":
                    product = new Product(code, priceP, materialS, typeProductS, dateRegister, sizeS, brandS, waterproofYes, weight, gadgetS, Security.NONE.toString(), false);
                    model.insertProduct(product);
                    reloadProductsActive();
                    cleanFormProduct();
                    break;
                case "SUITCASE":
                    if (checkValuesFormTravelBag(security, wheelsYes, wheelsNo)) {

                        String securityS = security.toString();
                        product = new Product(code, priceP, materialS, typeProductS, dateRegister, sizeS, brandS, waterproofYes, weight, Gadget.NONE.toString(), securityS, wheelsYes);
                        model.insertProduct(product);
                        reloadProductsActive();
                        cleanFormProduct();
                    }
                    break;
            }
        } else if (currentProduct != null) {
            currentProduct.setCode(code);
            currentProduct.setPrice(priceP);
            currentProduct.setMaterial(materialS);
            currentProduct.setTypeProduct(typeProductS);
            currentProduct.setRegisterDay(dateRegister);
            currentProduct.setSize(sizeS);
            currentProduct.setBrand(brandS);
            currentProduct.setWaterproof(waterproofYes);
            currentProduct.setWeight(weight);

            switch (typeProductS) {
                case "TRAVELBAG":
                case "BAG":
                    currentProduct.setGadget(gadgetS);
                    currentProduct.setSecurity(Security.NONE.toString());
                    currentProduct.setWheels(false);
                    break;
                case "SUITCASE":
                    if (!checkValuesFormTravelBag(security, wheelsYes, wheelsNo)) {
                        return;
                    }
                    String securityS = security.toString();
                    currentProduct.setGadget(Gadget.NONE.toString());
                    currentProduct.setSecurity(securityS);
                    currentProduct.setWheels(wheelsYes);
                    break;
            }
            System.out.println("currentProduct is: " + currentProduct);
            model.updateProduct(currentProduct);
            reloadProductsActive();
            cleanFormProduct();
        }


    }

    private void deleteProduct() {

        int row = dashboardView.tableAdminProducts.getSelectedRow();

        if (row < 0) {
            Utilities.showErrorAlert("Select a product in the table first.");
            return;
        }
        int idProduct = (int) dashboardView.tableAdminProducts.getValueAt(row, 0);
        String code = String.valueOf(dashboardView.tableAdminProducts.getValueAt(row, 1));
        Product productToDelete = new Product();
        productToDelete.setIdProduct(idProduct);
        productToDelete.setCode(code);
        model.deleteProduct(productToDelete);
        reloadProductsActive();
    }

    private void buyProduct() {

        if (!hasValidAddress()) {
            Utilities.showErrorAlert("You must create a new Address before buying a product");
            return;
        }

        int row = dashboardView.tableBags.getSelectedRow();

        if (row < 0) {
            Utilities.showErrorAlert("Select a product in the table first.");
            return;
        }

        Product product = new Product();
        int idProduct = (int) dashboardView.tableBags.getValueAt(row, 0);
        double price = (double) dashboardView.tableBags.getValueAt(row, 2);
        product.setIdProduct(idProduct);
        product.setPrice(price);
        System.out.println(SessionService.getCurrentUser() + " iduser");
        System.out.println(idProduct + " idProduct");
        System.out.println(price + " price");


        model.buyProduct(SessionService.getCurrentUser(), product);
        reloadProductsUserActive();
    }

    private boolean hasValidAddress() {
        Address address = SessionService.getCurrentUser().getAddress();

        return address != null
                && address.getCountry() != null
                && address.getCity() != null
                && address.getStreet() != null
                && address.getApartarment() != null;
    }


    private void deleteAddress() {
        System.out.println("Elimanado Address");
        int row = dashboardView.tablePreviewADInfo.getSelectedRow();

        if (row < 0) {
            Utilities.showErrorAlert("Select a product in the table first.");
            return;
        }

        int idAddres = (int) dashboardView.tablePreviewADInfo.getValueAt(row, 0);

        model.deleteAdddres(idAddres);
    }


    private void UpdateFromForm() {

        int row = dashboardView.tableAdminProducts.getSelectedRow();

        if (row < 0) {
            Utilities.showErrorAlert("Select a product first");
            return;
        }

        int idProduct = (int) dashboardView.tableAdminProducts.getValueAt(row, 0);
        String code = String.valueOf(dashboardView.tableAdminProducts.getValueAt(row, 1));
        dashboardView.txtAddCode.setText(code);

        Object price = dashboardView.tableAdminProducts.getValueAt(row, 2);
        double priceP = 0;

        if (price instanceof Number) {
            priceP = ((Number) price).doubleValue();
            dashboardView.spAddPrice.setValue(price);
        }

        String materialS = String.valueOf(dashboardView.tableAdminProducts.getValueAt(row, 3));
        Material material = Material.valueOf(materialS);
        dashboardView.cbAddMaterial.setSelectedItem(material);

        String typeProductS = String.valueOf(dashboardView.tableAdminProducts.getValueAt(row, 4));
        TypeProduct typeProduct = TypeProduct.valueOf(typeProductS);
        dashboardView.cbAddType.setSelectedItem(typeProduct);

        LocalDate registerDay = LocalDate.parse(String.valueOf(dashboardView.tableAdminProducts.getValueAt(row, 5)));
        dashboardView.dpAddDate.setDate(registerDay);

        String sizeS = String.valueOf(dashboardView.tableAdminProducts.getValueAt(row, 6));
        Size size = Size.valueOf(sizeS);
        dashboardView.cbAddSize.setSelectedItem(size);

        String brandS = String.valueOf(dashboardView.tableAdminProducts.getValueAt(row, 7));
        Brand brand = Brand.valueOf(brandS);
        dashboardView.cbAddBrand.setSelectedItem(brand);

        boolean isWateproof = Boolean.parseBoolean(String.valueOf(dashboardView.tableAdminProducts.getValueAt(row, 8)));

        if (isWateproof) {
            dashboardView.rbAddYesWater.setSelected(true);
        } else {
            dashboardView.rbAddNoWater.setSelected(true);
        }

        int weight = Integer.parseInt(String.valueOf(dashboardView.tableAdminProducts.getValueAt(row, 9)));
        dashboardView.slAddWeight.setValue(weight);

        switch (typeProduct) {
            case BAG:
            case TRAVELBAG:
                String gadgetS = String.valueOf(dashboardView.tableAdminProducts.getValueAt(row, 10));
                Gadget gadget = Gadget.valueOf(gadgetS);
                dashboardView.cbAddGadget.setSelectedItem(gadget);
                dashboardView.cbAddSecurity.setSelectedItem(Security.NONE);
                dashboardView.rbAddNoWheels.setSelected(false);
                dashboardView.rbAddYesWheels.setSelected(false);
                currentProduct = new Product(code, priceP, materialS, typeProductS, registerDay, sizeS, brandS, isWateproof, weight, gadgetS, Security.NONE.toString(), false);
                currentProduct.setIdProduct(idProduct);
                System.out.println("Update currentProduct is: " + currentProduct);
                break;
            case SUITCASE:
                String securityS = String.valueOf(dashboardView.tableAdminProducts.getValueAt(row, 11));
                Security security = Security.valueOf(securityS);
                dashboardView.cbAddSecurity.setSelectedItem(security);

                dashboardView.cbAddGadget.setSelectedItem(Gadget.NONE);
                boolean haveWheels = Boolean.parseBoolean(String.valueOf(dashboardView.tableAdminProducts.getValueAt(row, 12)));

                if (haveWheels) {
                    dashboardView.rbAddYesWheels.setSelected(true);
                } else {
                    dashboardView.rbAddNoWheels.setSelected(true);
                }

                currentProduct = new Product(code, priceP, materialS, typeProductS, registerDay, sizeS, brandS, isWateproof, weight, Gadget.NONE.toString(), securityS, haveWheels);
                currentProduct.setIdProduct(idProduct);
                System.out.println("Update currentProduct is: " + currentProduct);
                break;
        }

        Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "adminAddProduct");
    }


    private boolean checkValuesFormTravelBag(Object security, boolean wheelsYes, boolean wheelsNo) {

        if (security == null) {
            Utilities.showErrorAlert("select a Security");
            dashboardView.cbAddSecurity.requestFocus();
            return false;
        }

        if (!wheelsYes && !wheelsNo) {
            Utilities.showErrorAlert("Mark if has wheels");
            dashboardView.rbAddYesWheels.requestFocus();
            return false;
        }

        return true;
    }

    private boolean checkValuesFormNormal(Object typeProduct, LocalDate dateRegister, Object price, Object material, Object size, Object brand, int weight, boolean waterproofYes, boolean waterproofNo, Object gadget, String code) {

        if (typeProduct == null) {
            Utilities.showErrorAlert("Select a Type of Bag");
            dashboardView.cbAddType.requestFocus();
            return false;
        }

        if (code.isEmpty()) {
            Utilities.showErrorAlert("Type a code");
            dashboardView.txtAddCode.requestFocus();
            return false;
        }

        if (dateRegister == null) {
            Utilities.showErrorAlert("Select a date");
            dashboardView.dpAddDate.requestFocus();
            return false;
        }

        if (dateRegister.isBefore(LocalDate.now())) {
            Utilities.showErrorAlert("The date cannot be earlier than the current date");
            dashboardView.dpAddDate.requestFocus();
            return false;
        }

        if (price == null) {
            Utilities.showErrorAlert("Select a price");
            dashboardView.spAddPrice.requestFocus();
            return false;
        }

        if (material == null) {
            Utilities.showErrorAlert("Select a Material");
            dashboardView.cbAddMaterial.requestFocus();
            return false;
        }

        if (size == null) {
            Utilities.showErrorAlert("Select a Size");
            dashboardView.cbAddSize.requestFocus();
            return false;
        }

        if (brand == null) {
            Utilities.showErrorAlert("Select a brand");
            dashboardView.cbAddSize.requestFocus();
            return false;
        }

        if (weight <= 0) {
            Utilities.showErrorAlert("The weight must be greater than or equal to");
            dashboardView.slAddWeight.requestFocus();
            return false;
        }

        if (!waterproofYes && !waterproofNo) {
            Utilities.showErrorAlert("Mark if waterproof");
            dashboardView.rbAddYesWater.requestFocus();
            return false;
        }

        if (gadget == null) {
            Utilities.showErrorAlert("Select a gadget");
            dashboardView.cbAddGadget.requestFocus();
            return false;
        }
        return true;
    }


    private void cleanFormProduct() {
        currentProduct = null;
        dashboardView.txtAddCode.setText("");
        dashboardView.txtAddLastCode.setText("");
        dashboardView.cbAddType.setSelectedIndex(-1);
        dashboardView.dpAddDate.setDate(LocalDate.now());
        dashboardView.spAddPrice.setValue(1);
        dashboardView.cbAddMaterial.setSelectedIndex(-1);
        dashboardView.cbAddSize.setSelectedIndex(-1);
        dashboardView.cbAddBrand.setSelectedIndex(-1);
        dashboardView.slAddWeight.setValue(1);
        dashboardView.rbAddYesWater.setSelected(false);
        dashboardView.rbAddNoWater.setSelected(false);
        dashboardView.cbAddGadget.setSelectedIndex(-1);
        dashboardView.cbAddSecurity.setSelectedIndex(-1);
        dashboardView.rbAddNoWheels.setSelected(false);
        dashboardView.rbAddYesWheels.setSelected(false);
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && !((ListSelectionModel) e.getSource()).isSelectionEmpty()) {
            if (e.getSource().equals(dashboardView.tableBags.getSelectionModel())) {
                int row = dashboardView.tableBags.getSelectedRow();

                System.out.println(String.valueOf(dashboardView.tableBags.getValueAt(row, 0)));
                System.out.println(String.valueOf(dashboardView.tableBags.getValueAt(row, 1)));
                System.out.println(String.valueOf(dashboardView.tableBags.getValueAt(row, 2)));
                System.out.println(String.valueOf(dashboardView.tableBags.getValueAt(row, 3)));
                System.out.println(String.valueOf(dashboardView.tableBags.getValueAt(row, 4)));
                System.out.println(String.valueOf(dashboardView.tableBags.getValueAt(row, 5)));
                System.out.println(String.valueOf(dashboardView.tableBags.getValueAt(row, 6)));
                System.out.println(String.valueOf(dashboardView.tableBags.getValueAt(row, 7)));
                System.out.println(String.valueOf(dashboardView.tableBags.getValueAt(row, 8)));
                System.out.println(String.valueOf(dashboardView.tableBags.getValueAt(row, 9)));
                System.out.println(String.valueOf(dashboardView.tableBags.getValueAt(row, 10)));
                System.out.println(String.valueOf(dashboardView.tableBags.getValueAt(row, 11)));
            }
        }
    }

//    private void checkTypeProducto(){
//                        String typeFromTable = dashboardView.tableAdminProducts.getValueAt(row, 4).toString();
//                for (int i = 0; i < dashboardView.cbAddType.getItemCount(); i++) {
//                    String item = dashboardView.cbAddType.getItemAt(i).toString();
//                    if (item.equalsIgnoreCase(typeFromTable)) {
//                        dashboardView.cbAddType.setSelectedIndex(i);
//                        break;
//                    }
//                }
//    }

    @Override
    public void tableChanged(TableModelEvent e) {
        Object evt = e.getSource();

        if (evt.equals(dashboardView.dtmTableProducts)) {
            if (e.getType() == TableModelEvent.UPDATE) {


                int row = e.getFirstRow();

                if (row < 0) {
                    return;
                }

                System.out.println("actualizada");

                DefaultTableModel dtm = dashboardView.dtmTableProducts;

                int id = Integer.parseInt(dtm.getValueAt(row, 0).toString());

                String code = dtm.getValueAt(row, 1).toString();

                double price = Double.parseDouble(dtm.getValueAt(row, 2).toString());

                String material = dtm.getValueAt(row, 3).toString();

                String type = dtm.getValueAt(row, 4).toString();

                LocalDate registerDay = LocalDate.parse(dtm.getValueAt(row, 5).toString());

                String size = dtm.getValueAt(row, 6).toString();

                String brand = dtm.getValueAt(row, 7).toString();

                boolean waterproof = Boolean.parseBoolean(dtm.getValueAt(row, 8).toString());

                int weight = Integer.parseInt(dtm.getValueAt(row, 9).toString());

                String gadget = dtm.getValueAt(row, 10).toString();

                String security = dtm.getValueAt(row, 11).toString();

                boolean wheels = Boolean.parseBoolean(dtm.getValueAt(row, 12).toString());


                Product product = new Product(
                        code, price, material, type, registerDay,
                        size, brand, waterproof, weight, gadget, security, wheels
                );
                product.setIdProduct(id);

                model.updateProduct(product);

            }
        }

        if (evt.equals(dashboardView.dtmTablePreviewADInfo)) {
            if (e.getType() == TableModelEvent.UPDATE) {

                int row = e.getFirstRow();

                if (row < 0) {
                    return;
                }

                System.out.println("Addres desde tabla addres");
                DefaultTableModel dtm = dashboardView.dtmTablePreviewADInfo;

                int id = Integer.parseInt(dtm.getValueAt(row, 0).toString());

                String country = dtm.getValueAt(row, 1).toString();

                String city = dtm.getValueAt(row, 2).toString();

                String street = dtm.getValueAt(row, 3).toString();

                String apartarment = dtm.getValueAt(row, 4).toString();

                Address address = new Address();
                address.setCountry(country);
                address.setCity(city);
                address.setStreet(street);
                address.setApartarment(apartarment);
                User currentUser = SessionService.getCurrentUser();
                currentUser.setAddress(address);
                model.updateAddress(currentUser);
            }
        }


        if (evt.equals(dashboardView.dtmTablePreviewNormalInfo)) {
            if (e.getType() == TableModelEvent.UPDATE) {

                int row = e.getFirstRow();

                if (row < 0) {
                    return;
                }
                System.out.println("User desde tabla User");
                DefaultTableModel dtm = dashboardView.dtmTablePreviewNormalInfo;

                String[] comlums = {"iduser", "name", "lastname", "username", "email", "birthday", "balance"};

                int iduser = Integer.parseInt(dtm.getValueAt(row, 0).toString());

                String name = dtm.getValueAt(row, 1).toString();

                String lastname = dtm.getValueAt(row, 2).toString();

                String username = dtm.getValueAt(row, 3).toString();

                String email = dtm.getValueAt(row, 4).toString();

                LocalDate birthday = LocalDate.parse(dtm.getValueAt(row, 5).toString());

                double balance = Double.parseDouble(dtm.getValueAt(row, 6).toString());

                User user = new User();
                user.setId(iduser);
                user.setName(name);
                user.setLastName(lastname);
                user.setUserName(username);
                user.setPassword(SessionService.getCurrentUser().getPassword());
                user.setEmail(email);
                user.setBirthday(birthday);
                user.setBalance(balance);
                model.updateUser(user);
            }
        }

    }

    private void validateCity() {
        Country country = (Country) dashboardView.cbEditProfileCountry.getSelectedItem();

        if (country == null) {
            dashboardView.cbEditProfileCity.setModel(new DefaultComboBoxModel<>(new String[]{"SELECT FIRST A COUNTRY"}));
            return;
        }

        dashboardView.cbEditProfileCity.setEnabled(true);
        dashboardView.cbEditProfileCity.setModel(new DefaultComboBoxModel<>(Utilities.getCitiesByCountry(country)));
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object evt = e.getSource();

        if (evt.equals(dashboardView.cbEditProfileCountry)) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                validateCity();
            }
        }
    }

    private void centerAllTables() {
        centerTable(dashboardView.tableAdminProducts);
        centerTable(dashboardView.tableBags);
        centerTable(dashboardView.tableHistory);
        centerTable(dashboardView.tableHome);
        centerTable(dashboardView.tablePreviewADInfo);
    }


    private void centerTable(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(centerRenderer);
        }
    }
}






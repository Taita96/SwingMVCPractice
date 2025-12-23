package controller;

import model.Model;
import model.entity.Product;
import model.entity.enums.*;
import model.service.SessionService;
import util.Utilities;
import view.DashboardView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class DashboardController implements ActionListener, ListSelectionListener, TableModelListener {

    private DashboardView dashboardView;
    private Model model;
    private boolean isUpdated;
    private Product currentProduct;

    public DashboardController(DashboardView dashboardView, Model model) {
        this.dashboardView = dashboardView;
        this.model = model;
        initLogin();
        addActionListener(this);
        addListSelectionListener();
    }

    private void addListSelectionListener() {
        dashboardView.tableAdminProducts.setCellSelectionEnabled(true);
        ListSelectionModel tableAdminProducts = dashboardView.tableAdminProducts.getSelectionModel();
        tableAdminProducts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableAdminProducts.addListSelectionListener(this);
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
        dashboardView.btnProfileHome.addActionListener(e);
        dashboardView.btnProfileEdit.addActionListener(e);
        dashboardView.btnAddHome.addActionListener(e);
        dashboardView.btnAddSave.addActionListener(e);
        dashboardView.btnAddClean.addActionListener(e);
        dashboardView.btnConfigHome.addActionListener(e);
        dashboardView.btnConfigSave.addActionListener(e);
        dashboardView.btnConfigClean.addActionListener(e);
        dashboardView.btnHomeAdminNewProduct.addActionListener(e);
        dashboardView.btnHomeAdminUpdateForm.addActionListener(e);
        dashboardView.btnHomeAdminUpdateTable.addActionListener(e);
        dashboardView.cbAddType.addActionListener(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String evt = e.getActionCommand();

        switch (evt) {
            case "btnHome":
                showHome();
                break;
            case "btnHistory":
                Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "userHistory");
                break;
            case "btnBags":
                Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "userBags");
                break;
            case "btnProfile":
                Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "userViewProfile");
                break;
            case "btnSelling":
                Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "adminSales");
                break;
            case "btnAddProduct":
            case"btnHomeAdminNewProduct":
                Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "adminAddProduct");
                break;
            case "btnProfileHome":

                break;
            case "btnConfig":
                Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "adminConfig");
                break;
            case "btnProfileEdit":
                Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "userEditProfile");
                break;
            case "btnAddHome":

                break;
            case "btnAddSave":
                saveProduct();
                break;
            case "btnAddClean":
                cleanFormProduct();
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
            case "btnHomeAdminUpdateTable":

                break;
        }
    }

    private void UpdateFromForm() {
        int row = dashboardView.tableAdminProducts.getSelectedRow();

        if (row < 0) {
            Utilities.showErrorAlert("Select a product in the table first.");
            return;
        }
        int idProduct = (int) dashboardView.tableAdminProducts.getValueAt(row, 0);
        String code = String.valueOf(dashboardView.tableAdminProducts.getValueAt(row, 1));
        dashboardView.txtAddCode.setText(code);

        Object price = dashboardView.tableAdminProducts.getValueAt(row, 2);
        double priceP = 0 ;

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

        if(isWateproof){
            dashboardView.rbAddYesWater.setSelected(true);
        }else{
            dashboardView.rbAddNoWater.setSelected(true);
        }

        int weight = Integer.parseInt(String.valueOf(dashboardView.tableAdminProducts.getValueAt(row, 9)));
        dashboardView.slAddWeight.setValue(weight);

        switch (typeProduct){
            case BAG:
            case TRAVELBAG:
                String gadgetS = String.valueOf(dashboardView.tableAdminProducts.getValueAt(row, 10));
                Gadget gadget = Gadget.valueOf(gadgetS);
                dashboardView.cbAddGadget.setSelectedItem(gadget);
                dashboardView.cbAddSecurity.setSelectedItem(Security.NONE);
                dashboardView.rbAddNoWheels.setSelected(false);
                dashboardView.rbAddYesWheels.setSelected(false);
                currentProduct = new Product(code,priceP,materialS,typeProductS,registerDay,sizeS,brandS,isWateproof,weight,gadgetS,Security.NONE.toString(),false);
                currentProduct.setIdProduct(idProduct);
                break;
            case SUITCASE:
                String securityS = String.valueOf(dashboardView.tableAdminProducts.getValueAt(row, 11));
                Security security = Security.valueOf(securityS);
                dashboardView.cbAddSecurity.setSelectedItem(security);

                dashboardView.cbAddGadget.setSelectedItem(Gadget.NONE);
                boolean haveWheels = Boolean.parseBoolean(String.valueOf(dashboardView.tableAdminProducts.getValueAt(row, 12)));

                if(haveWheels){
                    dashboardView.rbAddYesWheels.setSelected(true);
                }else{
                    dashboardView.rbAddNoWheels.setSelected(true);
                }

                currentProduct = new Product(code,priceP,materialS,typeProductS,registerDay,sizeS,brandS,isWateproof,weight,Gadget.NONE.toString(),securityS,haveWheels);
                currentProduct.setIdProduct(idProduct);
                break;
        }
        System.out.println("Update currentProduct is: " + currentProduct);
        Utilities.displayCard((CardLayout) dashboardView.JPanelCard.getLayout(), dashboardView.JPanelCard, "adminAddProduct");
    }


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
                break;
            case "ADMIN":
                Utilities.displayCard(cl, dashboardView.JPanelCard, "adminHomeAdmin");
                Utilities.manageBtn(dashboardView.btnHistory, false);
                Utilities.manageBtn(dashboardView.btnBags, false);
                Utilities.manageBtn(dashboardView.btnProfile, false);
                initFormAddProduct();
                reloadProducts();
                break;
            default:
                System.out.println("Rol desconocido: " + rolName);
                break;
        }
    }

    private void reloadProducts() {

        List<Product> products = model.getAllProducts();
        String[] comlums = {"ID", "Code", "Price", "Material", "Type", "Registered", "Size", "Brand", "waterproof", "Weight", "Gadget", "Security", "Wheels"};
        DefaultTableModel modelTable = new DefaultTableModel(comlums, 0);

        for (Product product : products) {
            modelTable.addRow(new Object[]{
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
                    product.isWheels()
            });
        }

        dashboardView.tableAdminProducts.setModel(modelTable);
        isUpdated = false;
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
        if(currentProduct == null){
            switch (typeProductS) {
                case "TRAVELBAG":
                case "BAG":
                    product = new Product(code, priceP, materialS, typeProductS, dateRegister, sizeS, brandS, waterproofYes, weight, gadgetS, Security.NONE.toString(), false);
                    model.insertProduct(product);
                    reloadProducts();
                    cleanFormProduct();
                    break;
                case "SUITCASE":
                    if (checkValuesFormTravelBag(security, wheelsYes, wheelsNo)) {

                        String securityS = security.toString();
                        product = new Product(code, priceP, materialS, typeProductS, dateRegister, sizeS, brandS, waterproofYes, weight, Gadget.NONE.toString(), securityS, wheelsYes);
                        model.insertProduct(product);
                        reloadProducts();
                        cleanFormProduct();
                    }
                    break;
            }
        }else if(currentProduct != null){
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
            reloadProducts();
            cleanFormProduct();
        }


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

    }

}

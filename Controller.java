import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
public class Controller {
    private RegVendingMachine regularVM;
    private SpecialVendingMachine specialVM;
    private MenuView menuView;
    private CreateVMView createVMView; //Create VM for Menu
    private CreateVMMenu createVMMenu;
    private TestVMView testVMView;
    private TestVMMenu testVMMenu;
    private TestMaintenanceView testMaintenanceView;
    private StockView stockView;
    private ChangePriceView changePriceView;
    private CollectCoinView collectCoinView;
    private ReplenishMoneyView replenishMoneyView;

    public void openMainMenu() { //MAIN MENU WINDOW
        menuView = new MenuView();

        menuView.setCreateVMBttn(new ActionListener() { //Create a Vending Machine Button
            @Override
            public void actionPerformed(ActionEvent actionEvent) { //Create Vending Machine Button
                if (regularVM == null && specialVM == null) { //Check if a Vending Machine is already created
                    menuView.dispose();
                    openCreateMenu();
                } else {
                    JOptionPane.showOptionDialog(null, "A Vending Machine has already been created",
                            "Oops", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                }
            }
        });

        menuView.setTestVMBttn(new ActionListener() { // Test Vending Machine Button
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (regularVM != null || specialVM != null) { //Check if there is a Vending Machine to test
                    menuView.dispose();
                    openTestMenu();
                } else {
                    JOptionPane.showOptionDialog(null, "Please create a Vending Machine first",
                            "Oops", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                }
            }
        });

        menuView.setQuitButton(new ActionListener() { //Exit
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void openCreateMenu() { //CREATE VENDING MACHINE MENU
        createVMMenu = new CreateVMMenu();

        // Create Regular VM Button
        createVMMenu.setCreateRVMBttn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                createVMMenu.dispose();
                openCreateVM("Regular");
            }
        });

        // Create Special VM Button
        createVMMenu.setCreateSVMBttn(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createVMMenu.dispose();
                openCreateVM("Special");
            }
        });

        // Return to Main Menu Button
        createVMMenu.setReturnButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createVMMenu.dispose();
                openMainMenu();
            }
        });
    }

    // CREATE VENDING MACHINE
    public void openCreateVM(String VMType) {
        createVMView = new CreateVMView();

        if (VMType.equalsIgnoreCase("Regular"))
            createVMView.setHeadingLabel("Create Regular Vending Machine");
        else if (VMType.equalsIgnoreCase("Special"))
            createVMView.setHeadingLabel("Create Silog Vending Machine");

        // Clear Button
        createVMView.setClearButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createVMView.clearTextFields();
            }
        });

        // Create Button
        createVMView.setCreateButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name;
                int maxSlots;
                int maxCapacity;

                // Check if inputted maxSlots and maxCapacity are valid numbers
                try {
                    maxSlots = Integer.parseInt(createVMView.getSlotsTFText());
                    maxCapacity = Integer.parseInt(createVMView.getCapacityTFText());

                    // Check if maxSlots and maxCapacity reached the required minimum value
                    if (maxSlots < 8) {
                        JOptionPane.showOptionDialog(null, "No. of slots should be more than 8", "Invalid Input", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                        createVMView.clearTextFields();
                    }
                    if (maxCapacity < 10) {
                        JOptionPane.showOptionDialog(null, "Capacity of each slot should be more than 10", "Invalid Input", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                        createVMView.clearTextFields();
                    }

                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Invalid no. of slots or capacity input/s", "0", JOptionPane.ERROR_MESSAGE);
                    createVMView.clearTextFields();
                }

                // Create the Vending Machine
                name = createVMView.getNameTFText();
                maxSlots = Integer.parseInt(createVMView.getSlotsTFText());
                maxCapacity = Integer.parseInt(createVMView.getCapacityTFText());

                if (VMType.equalsIgnoreCase("Regular")) {
                    regularVM = new RegVendingMachine(name, maxSlots);
                    for (int i = 0; i < maxSlots; i++) {
                        regularVM.addSLot(maxCapacity);
                    }
                } else if (VMType.equalsIgnoreCase("Special")) {
                    specialVM = new SpecialVendingMachine(name, maxSlots);
                    // Add the slots
                    for (int i = 0; i < maxSlots; i++)
                        specialVM.addSLot(maxCapacity);
                }

                // display feedback message that the Vending Machine is created
                createVMView.setEnabledCreateButton(false);
                JOptionPane.showOptionDialog(null, "Vending Machine Created!\n\nName: " + name + "\nNo. of Slots: " + maxSlots + "\nMax Capacity per Slot: " + maxCapacity, "Request Complete", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, 0);
                createVMView.dispose();
                openMainMenu();
            }
        });

        // Cancel Button
        createVMView.setCancelButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createVMView.dispose();
                openCreateMenu();
                ;
            }
        });
    }

    // TEST VENDING MACHINE MENU
    public void openTestMenu() {
        testVMMenu = new TestVMMenu();

        testVMMenu.setVendingBttn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                testVMMenu.dispose();

                // Check which kind of Vending Machine was created
                if (regularVM != null && specialVM == null)
                    openTestVending("Regular");
                else if (regularVM == null && specialVM != null)
                    openTestVending("Special");
            }
        });

        // Test Maintenance Features Button
        testVMMenu.setMaintenanceBttn(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testVMMenu.dispose();
                // Check which kind of Vending Machine was created
                openTestMaintenance();
            }
        });

        // Return to Main Menu
        testVMMenu.setReturnButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testVMMenu.dispose();
                openMainMenu();
            }
        });
    }

    // TEST VENDING FEATURES
    public void openTestVending(String VMType) {
        if (VMType.equalsIgnoreCase("Regular")) {
            // If the instantiated VM is Regular
            testVMView = new TestVMView(regularVM);

            // disable Add and View Button for regular Vending Machine
            testVMView.setEnabledAddBtn(false);
            testVMView.setEnabledViewBtn(false);

            // Coin Buttons (how customers will pay)
            testVMView.setCoin20BListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setAmountTextAreaTA("20");
                    regularVM.getReceivedPayment().addCoins(1, 0, 0, 0);
                    regularVM.getMoneyBox().addCoins(1, 0, 0, 0);
                }
            });
            testVMView.setCoin10BListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    testVMView.setAmountTextAreaTA("10");
                    regularVM.getReceivedPayment().addCoins(0, 1, 0, 0);
                    regularVM.getMoneyBox().addCoins(0, 1, 0, 0);
                }
            });
            testVMView.setCoin5BListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    testVMView.setAmountTextAreaTA("5");
                    regularVM.getReceivedPayment().addCoins(0, 0, 1, 0);
                    regularVM.getMoneyBox().addCoins(0, 0, 1, 0);
                }
            });
            testVMView.setCoin1BListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    testVMView.setAmountTextAreaTA("1");
                    regularVM.getReceivedPayment().addCoins(0, 0, 0, 1);
                    regularVM.getMoneyBox().addCoins(0, 0, 0, 1);
                }
            });

            // Number Buttons (how customers select an item)
            testVMView.setButton1Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("1");
                }
            });
            testVMView.setButton2Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("2");
                }
            });
            testVMView.setButton3Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("3");
                }
            });
            testVMView.setButton4Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("4");
                }
            });
            testVMView.setButton5Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("5");
                }
            });
            testVMView.setButton6Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("6");
                }
            });
            testVMView.setButton7Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("7");
                }
            });
            testVMView.setButton8Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("8");
                }
            });
            testVMView.setButton9Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("9");
                }
            });
            testVMView.setButton0Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("0");
                }
            });

            // Clear Button (when customer decides to retype their slot no. input)
            testVMView.setClearBtnListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.clearAmountTextAreaTA();
                }
            });

            // Enter Button (when customer decides to buy the item)
            testVMView.setEnterBtnListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int paymentAmount;
                    int selectedSlotNum;

                    // Get the numerical value of inputs from text fields
                    paymentAmount = Integer.parseInt(testVMView.getAmountTextArea());
                    if (testVMView.getItemTextArea().equalsIgnoreCase(""))
                        selectedSlotNum = 0;
                    else
                        selectedSlotNum = Integer.parseInt(testVMView.getItemTextArea());

                    if (paymentAmount <= 0 && selectedSlotNum > 0) {
                        // If customer has not inserted any mony yet but has inputted a slot no.
                        JOptionPane.showOptionDialog(null, "Please insert money to use the Vending Machine",
                                "Request Denied", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                    } else if (paymentAmount > 0 && selectedSlotNum <= 0) {
                        // If customer has not inputted a slot no. yet but has inserted money into the VM
                        JOptionPane.showOptionDialog(null, "Please choose an item to purchase",
                                "Request Denied", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                    } else if (paymentAmount <= 0 && selectedSlotNum <= 0) {
                        // If both inputs are missing
                        JOptionPane.showOptionDialog(null, "Please insert money and choose an item to purchase",
                                "Request Denied", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                    } else if (paymentAmount > 0 && selectedSlotNum > 0) {
                        // If both inputs are provided

                        // Check if inputted slot no. is valid
                        if (selectedSlotNum <= 0 || selectedSlotNum > regularVM.getMaxSlots()) {
                            JOptionPane.showOptionDialog(null, "Please input a valid slot number", "Request Denied", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                            testVMView.clearItemTextAreaTA();
                        } else { // Check if slot is empty
                            if (regularVM.getSlots().get(selectedSlotNum - 1).isEmpty() &&
                                    selectedSlotNum > 0 && selectedSlotNum <= regularVM.getMaxSlots()) {
                                // if customer inputted valid slot number but is empty
                                JOptionPane.showConfirmDialog(null, "Slot is empty.", "Purchase Cancelled", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                                JOptionPane.showMessageDialog(null, "Returning...\n\nNo. of 20-peso coins: " + regularVM.getReceivedPayment().getNum20Coin() + "\nNo of 10-peso coins: " + regularVM.getReceivedPayment().getNum10Coin() +
                                                "\nNo of 5-peso coins: " + regularVM.getReceivedPayment().getNum5Coin() + "\nNo of 1-peso coins: " + regularVM.getReceivedPayment().getNum20Coin() + "\nTotal: " + regularVM.getReceivedPayment().computeTotalAmount(),
                                        "Purchase Cancelled", JOptionPane.PLAIN_MESSAGE);

                                regularVM.getMoneyBox().subtractCoins(regularVM.getReceivedPayment().getNum20Coin(), regularVM.getReceivedPayment().getNum10Coin(), regularVM.getReceivedPayment().getNum5Coin(), regularVM.getReceivedPayment().getNum1Coin());
                                regularVM.getReceivedPayment().subtractCoins(regularVM.getReceivedPayment().getNum20Coin(), regularVM.getReceivedPayment().getNum10Coin(), regularVM.getReceivedPayment().getNum5Coin(), regularVM.getReceivedPayment().getNum1Coin()); // empty the received payment
                                testVMView.clearTextFields();
                            } else {
                                Cash paymentMoney = regularVM.getReceivedPayment();
                                Cash dispensedChange;
                                Item dispensedItem;

                                if (paymentAmount > regularVM.getSlots().get(selectedSlotNum - 1).getItems().get(0).getPrice()) {
                                    // When change is expected to be dispensed
                                    dispensedChange = regularVM.dispenseChange(selectedSlotNum);
                                    dispensedItem = regularVM.dispensedItem(selectedSlotNum);

                                    // Check if VM was able to dispense change
                                    if (dispensedChange.computeTotalAmount() <= 0) {
                                        // If there should be change and the VM was not able to dispense change, then it has enough money in its moneyBox to provide change
                                        JOptionPane.showOptionDialog(null, "Vending Machine has insufficient money to provide change.", "Sorry for the inconvenience", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                                        JOptionPane.showMessageDialog(null, "Returning...\n\nNo. of 20-peso coins: " + paymentMoney.getNum20Coin() + "\nNo of 10-peso coins: " + paymentMoney.getNum10Coin() + "\nNo of 5-peso coins: " + paymentMoney.getNum5Coin() + "\nNo of 1-peso coins: " + paymentMoney.getNum1Coin() + "\nTotal: " + paymentMoney.computeTotalAmount(), "", JOptionPane.PLAIN_MESSAGE);
                                        // Return item in the slot
                                        regularVM.dispensedItem(selectedSlotNum);
                                        // Return money of customer
                                        regularVM.getMoneyBox().subtractCoins(regularVM.getReceivedPayment().getNum20Coin(), regularVM.getReceivedPayment().getNum10Coin(), regularVM.getReceivedPayment().getNum5Coin(), regularVM.getReceivedPayment().getNum1Coin());
                                        regularVM.getReceivedPayment().subtractCoins(regularVM.getReceivedPayment().getNum20Coin(), regularVM.getReceivedPayment().getNum10Coin(), regularVM.getReceivedPayment().getNum5Coin(), regularVM.getReceivedPayment().getNum1Coin()); // empty the received payment
                                        testVMView.clearTextFields();
                                    } else {
                                        // Add a transaction, dispense change & item
                                        regularVM.getTransactions().add(new Transaction(selectedSlotNum, dispensedItem.getName(), dispensedItem.getPrice()));
                                        JOptionPane.showMessageDialog(null, "Item Purchased\n\nDispensing Item...\n\n" + dispensedItem.getName() + "\nCalories: " + dispensedItem.getCalories(), "Enjoy!", JOptionPane.PLAIN_MESSAGE);
                                        JOptionPane.showMessageDialog(null, "Dispensing Change...\n\nNo. of 20-peso coins: " + dispensedChange.getNum20Coin() + "\nNo of 10-peso coins: " + dispensedChange.getNum10Coin() + "\nNo of 5-peso coins: " + dispensedChange.getNum5Coin() + "\nNo of 1-peso coins: " + dispensedChange.getNum1Coin() + "\nTotal: " + dispensedChange.computeTotalAmount() + "\n\nDispensing Item...\n\n" + dispensedItem.getName() + "\nCalories: " + dispensedItem.getCalories(), "", JOptionPane.PLAIN_MESSAGE);
                                        testVMView.clearTextFields();
                                    }
                                } else if (paymentAmount == regularVM.getSlots().get(selectedSlotNum - 1).getItems().get(0).getPrice()) {
                                    // When payment is exact and there is no need to dispense change
                                    dispensedItem = regularVM.dispensedItem(selectedSlotNum);

                                    // Add a transaction, dispense item
                                    regularVM.getTransactions().add(new Transaction(selectedSlotNum, dispensedItem.getName(), dispensedItem.getPrice()));
                                    JOptionPane.showMessageDialog(null, "Item Purchased\n\nDispensing Item...\n\n" + dispensedItem.getName() + "\nCalories: " + dispensedItem.getCalories(), "Enjoy!", JOptionPane.PLAIN_MESSAGE);
                                    testVMView.clearTextFields();
                                } else {
                                    // When payment is insufficient to purchase item
                                    dispensedItem = regularVM.dispensedItem(selectedSlotNum);

                                    JOptionPane.showOptionDialog(null, "Insufficient payment to purchase item.", "Oops", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                                    JOptionPane.showMessageDialog(null, "Returning...\n\nNo. of 20-peso coins: " + paymentMoney.getNum20Coin() + "\nNo of 10-peso coins: " + paymentMoney.getNum10Coin() + "\nNo of 5-peso coins: " + paymentMoney.getNum5Coin() + "\nNo of 1-peso coins: " + paymentMoney.getNum1Coin() + "\nTotal: " + paymentMoney.computeTotalAmount(), "", JOptionPane.PLAIN_MESSAGE);
                                    // Return item in the slot
                                    regularVM.dispensedItem(selectedSlotNum);
                                    // Return money of customer
                                    regularVM.getMoneyBox().subtractCoins(regularVM.getReceivedPayment().getNum20Coin(), regularVM.getReceivedPayment().getNum10Coin(), regularVM.getReceivedPayment().getNum5Coin(), regularVM.getReceivedPayment().getNum1Coin());
                                    regularVM.getReceivedPayment().subtractCoins(regularVM.getReceivedPayment().getNum20Coin(), regularVM.getReceivedPayment().getNum10Coin(), regularVM.getReceivedPayment().getNum5Coin(), regularVM.getReceivedPayment().getNum1Coin()); // empty the received payment
                                    testVMView.clearTextFields();
                                }
                            }
                        }
                    }
                    testVMView.dispose();
                    openTestVending(VMType);
                }
            });

            // Cancel Button
            testVMView.setCancelBtnListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (regularVM.getReceivedPayment().computeTotalAmount() > 0) {
                        JOptionPane.showOptionDialog(null, "Returning...\n\nNo. of 20-peso coins: " + regularVM.getReceivedPayment().getNum20Coin() + "\nNo of 10-peso coins: " + regularVM.getReceivedPayment().getNum10Coin() +
                                        "\nNo of 5-peso coins: " + regularVM.getReceivedPayment().getNum5Coin() + "\nNo of 1-peso coins: " + regularVM.getReceivedPayment().getNum20Coin() + "\nTotal: " + regularVM.getReceivedPayment().computeTotalAmount(),
                                "Purchase Cancelled", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, 0);
                        regularVM.getMoneyBox().subtractCoins(regularVM.getReceivedPayment().getNum20Coin(), regularVM.getReceivedPayment().getNum10Coin(), regularVM.getReceivedPayment().getNum5Coin(), regularVM.getReceivedPayment().getNum1Coin());
                        regularVM.getReceivedPayment().subtractCoins(regularVM.getReceivedPayment().getNum20Coin(), regularVM.getReceivedPayment().getNum10Coin(), regularVM.getReceivedPayment().getNum5Coin(), regularVM.getReceivedPayment().getNum1Coin());
                        testVMView.clearTextFields();
                        openTestMenu();
                    } else {
                        testVMView.clearTextFields();
                        testVMView.dispose();
                        openTestMenu();
                    }
                }
            });
        } else if (VMType.equalsIgnoreCase("Special")) {
            // If the instantiated VM is Special
            testVMView = new TestVMView(specialVM);

            // Coin Buttons (how customers will pay)
            testVMView.setCoin20BListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setAmountTextAreaTA("20");
                    specialVM.getReceivedPayment().addCoins(1, 0, 0, 0);
                    specialVM.getMoneyBox().addCoins(1, 0, 0, 0);
                }
            });
            testVMView.setCoin10BListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setAmountTextAreaTA("10");
                    specialVM.getReceivedPayment().addCoins(0, 1, 0, 0);
                    specialVM.getMoneyBox().addCoins(0, 1, 0, 0);
                }
            });
            testVMView.setCoin5BListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setAmountTextAreaTA("5");
                    specialVM.getReceivedPayment().addCoins(0, 0, 1, 0);
                    specialVM.getMoneyBox().addCoins(0, 0, 1, 0);
                }
            });
            testVMView.setCoin1BListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setAmountTextAreaTA("1");
                    specialVM.getReceivedPayment().addCoins(0, 0, 0, 1);
                    specialVM.getMoneyBox().addCoins(0, 0, 0, 1);
                }
            });

            // Number Buttons (how customers select an item)
            testVMView.setButton1Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("1");
                }
            });
            testVMView.setButton2Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("2");
                }
            });
            testVMView.setButton3Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("3");
                }
            });
            testVMView.setButton4Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("4");
                }
            });
            testVMView.setButton5Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("5");
                }
            });
            testVMView.setButton6Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("6");
                }
            });
            testVMView.setButton7Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("7");
                }
            });
            testVMView.setButton8Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("8");
                }
            });
            testVMView.setButton9Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("9");
                }
            });
            testVMView.setButton0Listener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.setItemTextAreaTA("0");
                }
            });

            // Clear Button
            testVMView.setClearBtnListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testVMView.clearAmountTextAreaTA();
                }
            });

            // Add Button
            testVMView.setAddBtnListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int selectedSlotNum;
                    Item selectedItem;

                    // check if text field is empty
                    if (testVMView.getItemTextArea().equalsIgnoreCase(""))
                        selectedSlotNum = 0;
                    else {
                        selectedSlotNum = Integer.parseInt(testVMView.getItemTextArea());
                        // Check if inputted slot no. is valid
                        if (selectedSlotNum <= 0 || selectedSlotNum > specialVM.getMaxSlots()) {
                            JOptionPane.showOptionDialog(null, "Please input a valid slot number", "Request Denied", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                        } else {
                            // Check if slot is empty
                            if (specialVM.getSlots().get(selectedSlotNum - 1).isEmpty()) {
                                JOptionPane.showConfirmDialog(null, "Slot is empty. Please choose another one.", "Request Denied", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                            } else {
                                selectedItem = specialVM.getSlots().get(selectedSlotNum - 1).getItems().get(0);
                                if (specialVM.addItemToCart(selectedItem)) {
                                    // If Vending Machine was able to add the item to cart
                                    JOptionPane.showMessageDialog(null, "Item successfully added to the cart!", "Item Added!", JOptionPane.PLAIN_MESSAGE);
                                } else {
                                    // If Vending Machine was NOT able to add the item to cart
                                    JOptionPane.showMessageDialog(null, "Failed to add item to cart.", "Request Denied", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }

                    testVMView.dispose();
                    openTestVending(VMType);
                }
            });

            // View Button
            testVMView.setViewBtnListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, specialVM.viewShoppingCart(), "Your Shopping Cart", JOptionPane.PLAIN_MESSAGE);
                }
            });

            // Enter Button
            testVMView.setEnterBtnListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int paymentAmount = Integer.parseInt(testVMView.getAmountTextArea());

                    // Check if customer can proceed
                    if (paymentAmount <= 0 && !specialVM.getShoppingCart().isEmpty()) {
                        // If customer has not inserted any money yet, but has added items to the cart
                        JOptionPane.showOptionDialog(null, "Please insert money to use the Vending Machine", "Request Denied", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                    } else if (paymentAmount > 0 && specialVM.getShoppingCart().isEmpty()) {
                        // If customer has inserted money but has not added anything to the cart
                        JOptionPane.showOptionDialog(null, "Please add an item to purchase in the shopping cart", "Request Denied", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                    } else if (paymentAmount <= 0 && specialVM.getShoppingCart().isEmpty()) {
                        // If customer both has not inserted money and added any items to the cart yet
                        JOptionPane.showOptionDialog(null, "Please insert money and add an item to purchase in the shopping cart", "Request Denied", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                    } else if (paymentAmount > 0 && !specialVM.getShoppingCart().isEmpty()) {
                        // If customer has filled the required fields
                        Cash paymentMoney = specialVM.getReceivedPayment();
                        Cash dispensedChange;
                        Silog dispensedCustomItem;

                        if (paymentAmount > specialVM.computeTotalPriceOfCart()) {
                            // When change is expected to be dispensed
                            dispensedChange = specialVM.dispenseChange();

                            // Check if VM is able to dispense change
                            if (dispensedChange.computeTotalAmount() == 0) {
                                // If there should be change and the VM was not able to dispense change, then it has enough money in its moneyBox to provide change
                                JOptionPane.showOptionDialog(null, "Vending Machine has insufficient money to provide change.", "Sorry for the inconvenience", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                                JOptionPane.showMessageDialog(null, "Returning...\n\nNo. of 20-peso coins: " + paymentMoney.getNum20Coin() + "\nNo of 10-peso coins: " + paymentMoney.getNum10Coin() + "\nNo of 5-peso coins: " + paymentMoney.getNum5Coin() + "\nNo of 1-peso coins: " + paymentMoney.getNum1Coin() + "\nTotal: " + paymentMoney.computeTotalAmount(), "", JOptionPane.PLAIN_MESSAGE);
                                // Return money of customer
                                specialVM.getMoneyBox().subtractCoins(paymentMoney.getNum20Coin(), paymentMoney.getNum10Coin(), paymentMoney.getNum5Coin(), paymentMoney.getNum1Coin());
                                specialVM.getReceivedPayment().subtractCoins(paymentMoney.getNum20Coin(), paymentMoney.getNum10Coin(), paymentMoney.getNum5Coin(), paymentMoney.getNum1Coin()); // empty the received payment
                                // empty the shopping cart
                                for (Item item : specialVM.getShoppingCart()) {
                                    specialVM.removeItemFromCart(item);
                                }
                                testVMView.clearTextFields();
                            } else {
                                // Check if items in shopping cart are enough to make a Sundae
                                if (specialVM.isPossibleForSilog()) {
                                    // It is possible to make an Ice Cream Sundae with the items in the cart
                                    int sundaePrice = specialVM.computeTotalPriceOfCart();
                                    int sundaeCalories = specialVM.computeTotalCaloriesOfCart();
                                    dispensedCustomItem = new Silog("Sundae", sundaePrice, sundaeCalories, specialVM.getShoppingCart());

                                    // Ask which container to use
                                    String[] buttons = {"Plate", "Tupperware"};
                                    int container;
                                    container = JOptionPane.showOptionDialog(null, "Select a container for your Silog",
                                            "Silog Container", JOptionPane.QUESTION_MESSAGE, 0, null, buttons, buttons[0]);

                                    // Prepare and dispense Sundae
                                    dispensedCustomItem = specialVM.makeSilogMeal();
                                    JOptionPane.showMessageDialog(null, dispensedCustomItem.toStringPreparation(container), "Please wait...", JOptionPane.PLAIN_MESSAGE);
                                    JOptionPane.showMessageDialog(null, "Dispensing Silog!\n\n" + dispensedCustomItem.toString(), "Enjoy!", JOptionPane.PLAIN_MESSAGE);

                                    // Add transaction
                                    for (Item selectedItem : specialVM.getShoppingCart()) {
                                        for (Slot slot : specialVM.getSlots()) {
                                            if (slot.getItems().get(0).getName().equalsIgnoreCase(selectedItem.getName()))
                                                specialVM.getTransactions().add(new Transaction(slot.getSlotNumber(), selectedItem.getName(), selectedItem.getPrice()));
                                            else
                                                specialVM.getTransactions().add(new Transaction(0, selectedItem.getName(), selectedItem.getPrice()));
                                        }
                                    }

                                    // dispense change
                                    JOptionPane.showMessageDialog(null, "Dispensing Change...\n\nNo. of 20-peso coins: " + dispensedChange.getNum20Coin() + "\nNo of 10-peso coins: " + dispensedChange.getNum10Coin() + "\nNo of 5-peso coins: " + dispensedChange.getNum5Coin() + "\nNo of 1-peso coins: " + dispensedChange.getNum1Coin() + "\nTotal: " + dispensedChange.computeTotalAmount(), "", JOptionPane.PLAIN_MESSAGE);
                                    // empty Shopping cart
                                    specialVM.getShoppingCart().clear();
                                } else {
                                    // It is not possible to make an Ice Cream Sundae with the items in the cart
                                    StringBuilder sb = new StringBuilder();
                                    int i = 1;
                                    for (Item item : specialVM.getShoppingCart()) {
                                        sb.append("Item #").append(i).append("\n");
                                        sb.append("   Name: ").append(item.getName()).append("\n");
                                        sb.append("   Calories: ").append(item.getCalories()).append("\n");
                                        i++;
                                    }

                                    // dispense item/s and change
                                    JOptionPane.showConfirmDialog(null, "Item/s in shopping cart is/are insufficient to make a custom Ice Cream Sundae. Item/s will be dispensed individually.", "Take note!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                                    JOptionPane.showMessageDialog(null, sb.toString(), "Enjoy!", JOptionPane.PLAIN_MESSAGE);
                                    JOptionPane.showMessageDialog(null, "Dispensing Change...\n\nNo. of 20-peso coins: " + dispensedChange.getNum20Coin() + "\nNo of 10-peso coins: " + dispensedChange.getNum10Coin() + "\nNo of 5-peso coins: " + dispensedChange.getNum5Coin() + "\nNo of 1-peso coins: " + dispensedChange.getNum1Coin() + "\nTotal: " + dispensedChange.computeTotalAmount(), "", JOptionPane.PLAIN_MESSAGE);
                                    // empty Shopping cart
                                    specialVM.getShoppingCart().clear();
                                    testVMView.clearTextFields();
                                }
                            }
                        } else if (paymentAmount == specialVM.computeTotalPriceOfCart()) {
                            // When payment is exact and there is no need to dispense change

                            // Check if items in shopping cart are enough to make a Sundae
                            if (specialVM.isPossibleForSilog()) {
                                // It is possible to make an Ice Cream Sundae with the items in the cart
                                int silogPrice = specialVM.computeTotalPriceOfCart();
                                int silogCalories = specialVM.computeTotalCaloriesOfCart();
                                dispensedCustomItem = new Silog("Sundae", silogPrice, silogCalories, specialVM.getShoppingCart());

                                // Ask which container to use
                                String[] buttons = {"Ice Cream Cone", "Paper Cup"};
                                int container;
                                container = JOptionPane.showOptionDialog(null, "Select a container for your Ice Cream Sundae", "Sundae Container", JOptionPane.QUESTION_MESSAGE, 0, null, buttons, buttons[0]);

                                // Prepare and dispense Sundae
                                dispensedCustomItem = specialVM.makeSilogMeal();
                                JOptionPane.showMessageDialog(null, dispensedCustomItem.toStringPreparation(container), "Please wait...", JOptionPane.PLAIN_MESSAGE);
                                JOptionPane.showMessageDialog(null, "Dispensing Ice Cream Sundae!\n\n" + dispensedCustomItem.toString(), "Enjoy!", JOptionPane.PLAIN_MESSAGE);

                                // Add transaction
                                for (Item selectedItem : specialVM.getShoppingCart()) {
                                    for (Slot slot : specialVM.getSlots()) {
                                        if (slot.getItems().get(0).getName().equalsIgnoreCase(selectedItem.getName()))
                                            specialVM.getTransactions().add(new Transaction(slot.getSlotNumber(), selectedItem.getName(), selectedItem.getPrice()));
                                        else
                                            specialVM.getTransactions().add(new Transaction(0, selectedItem.getName(), selectedItem.getPrice()));
                                    }
                                }

                                // empty Shopping cart
                                specialVM.getShoppingCart().clear();
                            } else {
                                // It is not possible to make an Ice Cream Sundae with the items in the cart
                                StringBuilder sb = new StringBuilder();
                                int i = 1;
                                for (Item item : specialVM.getShoppingCart()) {
                                    sb.append("Item #").append(i).append("\n");
                                    sb.append("   Name: ").append(item.getName()).append("\n");
                                    sb.append("   Calories: ").append(item.getCalories()).append("\n");
                                    i++;
                                }

                                // dispense item/s
                                JOptionPane.showConfirmDialog(null, "Item/s in shopping cart is/are insufficient to make a custom Ice Cream Sundae. Item/s will be dispensed individually.", "Take note!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                                JOptionPane.showMessageDialog(null, sb.toString(), "Enjoy!", JOptionPane.PLAIN_MESSAGE);
                                // empty Shopping cart
                                specialVM.getShoppingCart().clear();
                                testVMView.clearTextFields();
                            }
                        } else {
                            // When payment is insufficient to purchase the shopping cart
                            JOptionPane.showOptionDialog(null, "Insufficient payment to purchase item/s from shopping cart.", "Oops", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                            JOptionPane.showMessageDialog(null, "Returning...\n\nNo. of 20-peso coins: " + paymentMoney.getNum20Coin() + "\nNo of 10-peso coins: " + paymentMoney.getNum10Coin() + "\nNo of 5-peso coins: " + paymentMoney.getNum5Coin() + "\nNo of 1-peso coins: " + paymentMoney.getNum1Coin() + "\nTotal: " + paymentMoney.computeTotalAmount(), "", JOptionPane.PLAIN_MESSAGE);
                            // empty Shopping cart
                            specialVM.getShoppingCart().clear();
                            // Return money of customer
                            specialVM.getMoneyBox().subtractCoins(paymentMoney.getNum20Coin(), paymentMoney.getNum10Coin(), paymentMoney.getNum5Coin(), paymentMoney.getNum1Coin());
                            specialVM.getReceivedPayment().subtractCoins(paymentMoney.getNum20Coin(), paymentMoney.getNum10Coin(), paymentMoney.getNum5Coin(), paymentMoney.getNum1Coin()); // empty the received payment
                            testVMView.clearAmountTextAreaTA();
                        }
                    }

                    testVMView.dispose();
                    openTestVending(VMType);
                }
            });

            // Cancel Button
            testVMView.setCancelBtnListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Check if customer has inserted money
                    if (specialVM.getReceivedPayment().computeTotalAmount() > 0) {
                        JOptionPane.showOptionDialog(null, "Returning...\n\nNo. of 20-peso coins: " + specialVM.getReceivedPayment().getNum20Coin() + "\nNo of 10-peso coins: " + specialVM.getReceivedPayment().getNum10Coin() + "\nNo of 5-peso coins: " + specialVM.getReceivedPayment().getNum5Coin() + "\nNo of 1-peso coins: " + specialVM.getReceivedPayment().getNum20Coin() + "\nTotal: " + specialVM.getReceivedPayment().computeTotalAmount(), "Purchase Cancelled", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, 0);
                        specialVM.getMoneyBox().subtractCoins(specialVM.getReceivedPayment().getNum20Coin(), specialVM.getReceivedPayment().getNum10Coin(), specialVM.getReceivedPayment().getNum5Coin(), specialVM.getReceivedPayment().getNum1Coin());
                        specialVM.getReceivedPayment().subtractCoins(specialVM.getReceivedPayment().getNum20Coin(), specialVM.getReceivedPayment().getNum10Coin(), specialVM.getReceivedPayment().getNum5Coin(), specialVM.getReceivedPayment().getNum1Coin());
                        testVMView.clearTextFields();
                        openTestMenu();
                    } else {
                        testVMView.clearTextFields();
                        testVMMenu.dispose();
                        openTestMenu();
                    }
                }
            });
        }
    }

    // TEST MAINTENANCE FEATURES
    public void openTestMaintenance() {
        testMaintenanceView = new TestMaintenanceView();

        // Stock / Restock Button
        testMaintenanceView.setStockBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testMaintenanceView.dispose();

                // Check which kind of Vending Machine was created
                if (regularVM != null && specialVM == null)
                    openStock("Regular");
                else if (regularVM == null && specialVM != null)
                    openStock("Special");
            }
        });
        // Change Price Button
        testMaintenanceView.setChangePriceBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testMaintenanceView.dispose();

                // Check which kind of Vending Machine was created
                if (regularVM != null && specialVM == null)
                    openChangePrice("Regular");
                else if (regularVM == null && specialVM != null)
                    openChangePrice("Special");
            }
        });

        // Collect Money Button
        testMaintenanceView.setCollectMoneyBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testMaintenanceView.dispose();

                // Check which kind of Vending Machine was created
                if (regularVM != null && specialVM == null)
                    openCollectMoney("Regular");
                else if (regularVM == null && specialVM != null)
                    openCollectMoney("Special");
            }
        });

        // Replenish Money Button
        testMaintenanceView.setReplenishMoneyBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testMaintenanceView.dispose();

                // Check which kind of Vending Machine was created
                if (regularVM != null && specialVM == null)
                    openReplenishMoney("Regular");
                else if (regularVM == null && specialVM != null)
                    openReplenishMoney("Special");
            }
        });

        // Print Transaction Button
        testMaintenanceView.setPrintTransactionBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testMaintenanceView.dispose();

                // Check which kind of Vending Machine was created
                if (regularVM != null && specialVM == null)
                    openPrintTransactions("Regular");
                else if (regularVM == null && specialVM != null)
                    openPrintTransactions("Special");
            }
        });

        // Return to TestVM Menu Button
        testMaintenanceView.setReturnBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testMaintenanceView.dispose();
                openTestMenu();
            }
        });
    }

    // STOCK / RESTOCK
    public void openStock (String VMType) {
        stockView = new StockView();

        if (VMType.equalsIgnoreCase("Regular")) {
            // If the instantiated VM is Regular

            // View Inventory Button
            stockView.setViewInvBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    String inventoryString = "Slot No.               Item               Price (PHP)              Quantity\n";
                    for (Slot slot : regularVM.getSlots()) {
                        if (!slot.isEmpty()) {
                            inventoryString += slot.getSlotNumber() + "                 " +
                                    slot.getItems().get(0).getName() + "                 " +
                                    slot.getItems().get(0).getPrice() + "                 " +
                                    slot.getItems().size() + " / " + slot.getMaxCapacity() + "\n";
                        } else {
                            inventoryString += slot.getSlotNumber() + "\n";
                        }
                    }

                    stockView.setInvTxtArea(inventoryString);
                }
            });

            // Enter Button
            stockView.setEnterBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    int selectedSlotNum;

                    // record ending inventory
                    regularVM.recordEndInventory();

                    // Check if the inputted slot no. is valid
                    try {
                        selectedSlotNum = Integer.parseInt(stockView.getSlotNumTF());

                        if (selectedSlotNum <= 0 || selectedSlotNum > regularVM.getMaxSlots()) {
                            JOptionPane.showOptionDialog(null, "Invalid slot number", "Invalid Input", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                            stockView.clearSlotNumTxtFld();
                        }
                    } catch (NumberFormatException exception) {
                        JOptionPane.showOptionDialog(null, "Invalid slot number", "Invalid Input", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                        stockView.clearSlotNumTxtFld();
                    }

                    selectedSlotNum = Integer.parseInt(stockView.getSlotNumTF());

                    if (!regularVM.getSlots().get(selectedSlotNum - 1).isEmpty()) {
                        // If slot selected is empty
                        Item addItem = regularVM.getSlots().get(selectedSlotNum - 1).getItems().get(0);
                        String quantityString;
                        int quantity;

                        // Ask user to input quantity of items to stock
                        quantityString = JOptionPane.showInputDialog("Enter quantity of items to stock");
                        quantity = Integer.parseInt(quantityString);

                        // Check if there are excess items
                        int slotCapacity = regularVM.getSlots().get(selectedSlotNum - 1).getMaxCapacity();
                        int numOfItems   = regularVM.getSlots().get(selectedSlotNum - 1).getItems().size();
                        int excess = (numOfItems + quantity) - slotCapacity;
                        if (excess > 0) {
                            // If the quantity of items did not exceed slot capacity
                            regularVM.stock(selectedSlotNum, addItem, quantity);
                            stockView.clearSlotNumTxtFld();
                        } else {
                            // If the quantity of items exceed slot capacity
                            JOptionPane.showMessageDialog(null, "Quantity of items exceeds slot capacity. Excess items will not be added.", "Too many!", JOptionPane.ERROR_MESSAGE);
                            quantity = regularVM.getSlots().get(selectedSlotNum - 1).getMaxCapacity() - regularVM.getSlots().get(selectedSlotNum - 1).getItems().size();
                            regularVM.stock(selectedSlotNum, addItem, quantity);
                            JOptionPane.showMessageDialog(null, "Item/s successfully added to the slot", "Stocking Complete!", JOptionPane.DEFAULT_OPTION);
                            stockView.clearSlotNumTxtFld();
                        }
                    }else {
                        // If slot selected is not empty
                        Item addItem;
                        String itemName, itemPriceString, itemCalString, quantityString;
                        int itemPrice, itemCal, quantity;

                        // Ask user for item name, price, calories, and the quantity to add
                        itemName        = JOptionPane.showInputDialog("Enter name of item to add: ");
                        itemPriceString = JOptionPane.showInputDialog("Enter price of item to add: ");
                        itemCalString   = JOptionPane.showInputDialog("Enter no. of calories of item to add: ");
                        quantityString  = JOptionPane.showInputDialog("Enter quantity of items to add: ");

                        itemPrice = Integer.parseInt(itemPriceString);
                        itemCal   = Integer.parseInt(itemCalString);
                        quantity  = Integer.parseInt(quantityString);

                        addItem = new Item(itemName, itemPrice, itemCal);
                        regularVM.stock(selectedSlotNum, addItem, quantity);
                        JOptionPane.showMessageDialog(null, "Item/s successfully added to the slot", "Stocking Complete!", JOptionPane.DEFAULT_OPTION);
                        stockView.clearSlotNumTxtFld();
                    }
                }
            });

            // Cancel Button
            stockView.setCancelBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    // record starting inventory
                    regularVM.recordStartInventory();

                    stockView.dispose();
                    openTestMaintenance();
                }
            });

        } else if (VMType.equalsIgnoreCase("Special")) {
            // If the instantiated VM is Special

            // View Inventory Button
            stockView.setViewInvBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    String inventoryString = "Slot No.               Item               Price (PHP)              Quantity\n";
                    for (Slot slot : specialVM.getSlots()) {
                        if (!slot.isEmpty()) {
                            inventoryString += slot.getSlotNumber() + "                 " +
                                    slot.getItems().get(0).getName() + "                 " +
                                    slot.getItems().get(0).getPrice() + "                 " +
                                    slot.getItems().size() + " / " + slot.getMaxCapacity() + "\n";
                        } else {
                            inventoryString += slot.getSlotNumber() + "\n";
                        }
                    }

                    stockView.setInvTxtArea(inventoryString);
                }
            });

            // Enter Button
            stockView.setEnterBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    int selectedSlotNum;

                    // record ending inventory
                    specialVM.recordEndInventory();

                    // Check if the inputted slot no. is valid
                    try {
                        selectedSlotNum = Integer.parseInt(stockView.getSlotNumTF());

                        if (selectedSlotNum <= 0 || selectedSlotNum > specialVM.getMaxSlots()) {
                            JOptionPane.showOptionDialog(null, "Invalid slot number", "Invalid Input", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                            stockView.clearSlotNumTxtFld();
                        }
                    } catch (NumberFormatException exception) {
                        JOptionPane.showOptionDialog(null, "Invalid slot number", "Invalid Input", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                        stockView.clearSlotNumTxtFld();
                    }

                    selectedSlotNum = Integer.parseInt(stockView.getSlotNumTF());

                    if (!specialVM.getSlots().get(selectedSlotNum - 1).isEmpty()) {
                        // If slot selected is empty
                        Item addItem = specialVM.getSlots().get(selectedSlotNum - 1).getItems().get(0);
                        String quantityString;
                        int quantity;

                        // Ask user to input quantity of items to stock
                        quantityString = JOptionPane.showInputDialog("Enter quantity of items to stock");
                        quantity = Integer.parseInt(quantityString);

                        // Check if there are excess items
                        int slotCapacity = specialVM.getSlots().get(selectedSlotNum - 1).getMaxCapacity();
                        int numOfItems = specialVM.getSlots().get(selectedSlotNum - 1).getItems().size();
                        int excess = (numOfItems + quantity) - slotCapacity;
                        if (excess > 0) {
                            // If the quantity of items did not exceed slot capacity
                            specialVM.stock(selectedSlotNum, addItem, quantity);
                            stockView.clearSlotNumTxtFld();
                        } else {
                            // If the quantity of items exceed slot capacity
                            JOptionPane.showMessageDialog(null, "Quantity of items exceeds slot capacity. Excess items will not be added.", "Too many!", JOptionPane.ERROR_MESSAGE);
                            quantity = specialVM.getSlots().get(selectedSlotNum - 1).getMaxCapacity() - specialVM.getSlots().get(selectedSlotNum - 1).getItems().size();
                            specialVM.stock(selectedSlotNum, addItem, quantity);
                            JOptionPane.showMessageDialog(null, "Item/s successfully added to the slot", "Stocking Complete!", JOptionPane.DEFAULT_OPTION);
                            stockView.clearSlotNumTxtFld();
                        }
                    } else {
                        // If slot selected is not empty
                        Item addItem;
                        String itemName, itemPriceString, itemCalString, quantityString;
                        int itemPrice, itemCal, quantity;

                        // Ask user for item name, price, calories, and the quantity to add
                        itemName        = JOptionPane.showInputDialog("Enter name of item to add: ");
                        itemPriceString = JOptionPane.showInputDialog("Enter price of item to add: ");
                        itemCalString   = JOptionPane.showInputDialog("Enter no. of calories of item to add: ");
                        quantityString  = JOptionPane.showInputDialog("Enter quantity of items to add: ");

                        itemPrice = Integer.parseInt(itemPriceString);
                        itemCal   = Integer.parseInt(itemCalString);
                        quantity  = Integer.parseInt(quantityString);

                        addItem = new Item(itemName, itemPrice, itemCal);
                        specialVM.stock(selectedSlotNum, addItem, quantity);
                        JOptionPane.showMessageDialog(null, "Item/s successfully added to the slot", "Stocking Complete!", JOptionPane.DEFAULT_OPTION);
                        stockView.clearSlotNumTxtFld();
                    }
                }
            });

            // Cancel Button
            stockView.setCancelBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    // record starting inventory
                    specialVM.recordStartInventory();

                    stockView.dispose();
                    openTestMaintenance();
                }
            });
        }
    }

    // CHANGE PRICE
    public void openChangePrice (String VMType) {
        changePriceView = new ChangePriceView();

        if (VMType.equalsIgnoreCase("Regular")) {
            // If the instantiated VM is Regular

            // View Price Button
            changePriceView.setViewPriceBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {

                    String pricesString = "Item                    Price\n";
                    for (Slot slot : regularVM.getSlots()) {
                        if (!slot.isEmpty()) {
                            pricesString += slot.getItems().get(0).getName() + "                 PHP " +
                                    slot.getItems().get(0).getPrice() + "\n";
                        }

                        changePriceView.setViewPricesArea(pricesString);;
                    }
                }
            });

            // Enter Button
            changePriceView.setEnterBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    String itemName = changePriceView.getItemTF();
                    String newPriceString;
                    int newPrice;

                    // check if text field is empty
                    if (changePriceView.getItemTF().equalsIgnoreCase("")) {
                        JOptionPane.showOptionDialog(null, "Input is invalid", "Invalid Input", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                        changePriceView.clearItemTxtFld();
                    } else {
                        boolean isFound = false;
                        for (Slot slot : regularVM.getSlots()) {
                            if (!slot.isEmpty()) {
                                if (slot.getItems().get(0).getName().equalsIgnoreCase(itemName))
                                    isFound = true;
                            }
                        }

                        if (!isFound) {
                            JOptionPane.showMessageDialog(null, "Item not found", "Oops", JOptionPane.ERROR_MESSAGE);
                        } else {
                            // Ask user to input new price
                            newPriceString = JOptionPane.showInputDialog("Enter new price of item: ");
                            newPrice = Integer.parseInt(newPriceString);

                            regularVM.setPrice(itemName, newPrice);
                            JOptionPane.showMessageDialog(null, "Price of item successfully changed!", "Request Complete", JOptionPane.PLAIN_MESSAGE);
                        }
                        changePriceView.clearItemTxtFld();
                    }
                }
            });

            // Cancel Button
            changePriceView.setCancelBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    changePriceView.dispose();
                    openTestMaintenance();
                }
            });
        } else if (VMType.equalsIgnoreCase("Special")) {
            // If the instantiated VM is Special

            // View Price Button
            changePriceView.setViewPriceBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {

                    String pricesString = "Item                    Price\n";
                    for (Slot slot : specialVM.getSlots()) {
                        if (!slot.isEmpty()) {
                            pricesString += slot.getItems().get(0).getName() + "                 PHP " +
                                    slot.getItems().get(0).getPrice() + "\n";
                        }

                        changePriceView.setViewPricesArea(pricesString);;
                    }
                }
            });

            // Enter Button
            changePriceView.setEnterBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    String itemName = changePriceView.getItemTF();
                    String newPriceString;
                    int newPrice;

                    // check if text field is empty
                    if (changePriceView.getItemTF().equalsIgnoreCase("")) {
                        JOptionPane.showOptionDialog(null, "Input is invalid", "Invalid Input", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                        changePriceView.clearItemTxtFld();
                    } else {
                        boolean isFound = false;
                        for (Slot slot : specialVM.getSlots()) {
                            if (!slot.isEmpty()) {
                                if (slot.getItems().get(0).getName().equalsIgnoreCase(itemName))
                                    isFound = true;
                            }
                        }

                        if (!isFound) {
                            JOptionPane.showMessageDialog(null, "Item not found", "Oops", JOptionPane.ERROR_MESSAGE);
                        } else {
                            // Ask user to input new price
                            newPriceString = JOptionPane.showInputDialog("Enter new price of item: ");
                            newPrice = Integer.parseInt(newPriceString);

                            specialVM.setPrice(itemName, newPrice);
                            JOptionPane.showMessageDialog(null, "Price of item successfully changed!", "Request Complete", JOptionPane.PLAIN_MESSAGE);
                        }
                        changePriceView.clearItemTxtFld();
                    }
                }
            });

            // Cancel Button
            changePriceView.setCancelBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    changePriceView.dispose();
                    openTestMaintenance();
                }
            });
        }
    }

    // COLLECT MONEY
    public void openCollectMoney (String VMType) {
        collectCoinView = new CollectCoinView();

        if (VMType.equalsIgnoreCase("Regular")) {
            // If the instantiated VM is Regular

            // View Money Box
            collectCoinView.setViewBttnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    Cash moneyBox = regularVM.getMoneyBox();

                    String coinsString = "";

                    coinsString += "No. of 20-peso coins: " + moneyBox.getNum20Coin() + "\n" +
                            "No. of 10-peso coins: " + moneyBox.getNum10Coin() + "\n" +
                            "No. of 5-peso coins:  " + moneyBox.getNum5Coin() + "\n" +
                            "No. of 1-peso coins:  " + moneyBox.getNum1Coin() + "\n" +
                            "Total: " + moneyBox.computeTotalAmount();

                    collectCoinView.setDisplayTextArea(coinsString);
                }
            });

            // Enter
            collectCoinView.setEnterBttnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    int collected20Coin, collected10Coin, collected5Coin, collected1Coin;

                    int num20Coin = Integer.parseInt(collectCoinView.getCoin20BTF());
                    int num10Coin = Integer.parseInt(collectCoinView.getCoin10BTF());
                    int num5Coin  = Integer.parseInt(collectCoinView.getCoin5BTF());
                    int num1Coin  = Integer.parseInt(collectCoinView.getCoin1BTF());

                    // Collect 20
                    if (num20Coin > regularVM.getMoneyBox().getNum20Coin()) {
                        JOptionPane.showMessageDialog(null, "The 20-peso coins in the money box is not enough for the amount you asked for. All of it will instead be dispensed", "Too Many!", JOptionPane.ERROR_MESSAGE);
                        collected20Coin = regularVM.getMoneyBox().getNum20Coin();
                        regularVM.getMoneyBox().subtractCoins(collected20Coin, 0, 0, 0);
                    } else {
                        collected20Coin = num20Coin;
                        regularVM.getMoneyBox().subtractCoins(collected20Coin, 0, 0, 0);
                    }

                    // Collect 10
                    if (num10Coin > regularVM.getMoneyBox().getNum10Coin()) {
                        JOptionPane.showMessageDialog(null, "The 10-peso coins in the money box is not enough for the amount you asked for. All of it will instead be dispensed", "Too Many!", JOptionPane.ERROR_MESSAGE);
                        collected10Coin = regularVM.getMoneyBox().getNum10Coin();
                        regularVM.getMoneyBox().subtractCoins(0, collected10Coin, 0, 0);
                    } else {
                        collected10Coin = num10Coin;
                        regularVM.getMoneyBox().subtractCoins(0, collected10Coin, 0, 0);
                    }

                    // Collect 5
                    if (num5Coin > regularVM.getMoneyBox().getNum5Coin()) {
                        JOptionPane.showMessageDialog(null, "The 5-peso coins in the money box is not enough for the amount you asked for. All of it will instead be dispensed", "Too Many!", JOptionPane.ERROR_MESSAGE);
                        collected5Coin = regularVM.getMoneyBox().getNum5Coin();
                        regularVM.getMoneyBox().subtractCoins(0, 0, collected5Coin, 0);
                    } else {
                        collected5Coin = num5Coin;
                        regularVM.getMoneyBox().subtractCoins(0, 0, collected5Coin, 0);
                    }

                    // Collect 1
                    if (num1Coin > regularVM.getMoneyBox().getNum1Coin()) {
                        JOptionPane.showMessageDialog(null, "The 1-peso coins in the money box is not enough for the amount you asked for. All of it will instead be dispensed", "Too Many!", JOptionPane.ERROR_MESSAGE);
                        collected1Coin = regularVM.getMoneyBox().getNum1Coin();
                        regularVM.getMoneyBox().subtractCoins(collected20Coin, 0, 0, collected1Coin);
                    } else {
                        collected1Coin = num1Coin;
                        regularVM.getMoneyBox().subtractCoins(0, 0, 0, collected1Coin);
                    }

                    // dispense collected money
                    Cash collectedMoney = new Cash(collected20Coin, collected10Coin, collected5Coin, collected1Coin);
                    JOptionPane.showMessageDialog(null, "Dispensing collected coins...\n\nNo. of 20-peso coins: " + collected20Coin + "\nNo. of 10-peso coins: " + collected10Coin + "\nNo. of 5-peso coins: " + collected5Coin + "\nNo. of 1-peso coins: " + collected1Coin + "\n\nTotal Amount: " + collectedMoney.computeTotalAmount(), "Money Collected!", JOptionPane.PLAIN_MESSAGE);
                    collectCoinView.clearAllTextFields();
                }
            });

            // Back
            collectCoinView.setBackBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    collectCoinView.dispose();
                    openTestMaintenance();
                }
            });

        } else if (VMType.equalsIgnoreCase("Special")) {
            // If the instantiated VM is Special

            // View Money Box
            collectCoinView.setViewBttnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    Cash moneyBox = specialVM.getMoneyBox();

                    String coinsString = "";

                    coinsString += "No. of 20-peso coins: " + moneyBox.getNum20Coin() + "\n" +
                            "No. of 10-peso coins: " + moneyBox.getNum10Coin() + "\n" +
                            "No. of 5-peso coins:  " + moneyBox.getNum5Coin() + "\n" +
                            "No. of 1-peso coins:  " + moneyBox.getNum1Coin() + "\n" +
                            "Total: " + moneyBox.computeTotalAmount();

                    collectCoinView.setDisplayTextArea(coinsString);
                }
            });

            // Enter
            collectCoinView.setEnterBttnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    int collected20Coin, collected10Coin, collected5Coin, collected1Coin;

                    int num20Coin = Integer.parseInt(collectCoinView.getCoin20BTF());
                    int num10Coin = Integer.parseInt(collectCoinView.getCoin10BTF());
                    int num5Coin  = Integer.parseInt(collectCoinView.getCoin5BTF());
                    int num1Coin  = Integer.parseInt(collectCoinView.getCoin1BTF());

                    // Collect 20
                    if (num20Coin > specialVM.getMoneyBox().getNum20Coin()) {
                        JOptionPane.showMessageDialog(null, "The 20-peso coins in the money box is not enough for the amount you asked for. All of it will instead be dispensed", "Too Many!", JOptionPane.ERROR_MESSAGE);
                        collected20Coin = specialVM.getMoneyBox().getNum20Coin();
                        specialVM.getMoneyBox().subtractCoins(collected20Coin, 0, 0, 0);
                    } else {
                        collected20Coin = num20Coin;
                        specialVM.getMoneyBox().subtractCoins(collected20Coin, 0, 0, 0);
                    }

                    // Collect 10
                    if (num10Coin > specialVM.getMoneyBox().getNum10Coin()) {
                        JOptionPane.showMessageDialog(null, "The 10-peso coins in the money box is not enough for the amount you asked for. All of it will instead be dispensed", "Too Many!", JOptionPane.ERROR_MESSAGE);
                        collected10Coin = specialVM.getMoneyBox().getNum10Coin();
                        specialVM.getMoneyBox().subtractCoins(0, collected10Coin, 0, 0);
                    } else {
                        collected10Coin = num10Coin;
                        specialVM.getMoneyBox().subtractCoins(0, collected10Coin, 0, 0);
                    }

                    // Collect 5
                    if (num5Coin > specialVM.getMoneyBox().getNum5Coin()) {
                        JOptionPane.showMessageDialog(null, "The 5-peso coins in the money box is not enough for the amount you asked for. All of it will instead be dispensed", "Too Many!", JOptionPane.ERROR_MESSAGE);
                        collected5Coin = specialVM.getMoneyBox().getNum5Coin();
                        specialVM.getMoneyBox().subtractCoins(0, 0, collected5Coin, 0);
                    } else {
                        collected5Coin = num5Coin;
                        specialVM.getMoneyBox().subtractCoins(0, 0, collected5Coin, 0);
                    }

                    // Collect 1
                    if (num1Coin > specialVM.getMoneyBox().getNum1Coin()) {
                        JOptionPane.showMessageDialog(null, "The 1-peso coins in the money box is not enough for the amount you asked for. All of it will instead be dispensed", "Too Many!", JOptionPane.ERROR_MESSAGE);
                        collected1Coin = specialVM.getMoneyBox().getNum1Coin();
                        specialVM.getMoneyBox().subtractCoins(collected20Coin, 0, 0, collected1Coin);
                    } else {
                        collected1Coin = num1Coin;
                        specialVM.getMoneyBox().subtractCoins(0, 0, 0, collected1Coin);
                    }

                    // dispense collected money
                    Cash collectedMoney = new Cash(collected20Coin, collected10Coin, collected5Coin, collected1Coin);
                    JOptionPane.showMessageDialog(null, "Dispensing collected coins...\n\nNo. of 20-peso coins: " + collected20Coin + "\nNo. of 10-peso coins: " + collected10Coin + "\nNo. of 5-peso coins: " + collected5Coin + "\nNo. of 1-peso coins: " + collected1Coin + "\n\nTotal Amount: " + collectedMoney.computeTotalAmount(), "Money Collected!", JOptionPane.PLAIN_MESSAGE);
                    collectCoinView.clearAllTextFields();
                }
            });

            // Back
            collectCoinView.setBackBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    collectCoinView.dispose();
                    openTestMaintenance();
                }
            });
        }
    }

    // REPLENISH MONEY
    public void openReplenishMoney (String VMType) {
        replenishMoneyView = new ReplenishMoneyView();

        if (VMType.equalsIgnoreCase("Regular")) {
            // If the instantiated VM is Regular

            // View
            replenishMoneyView.setViewBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    Cash moneyBox = regularVM.getMoneyBox();

                    String coinsString = "";

                    coinsString += "No. of 20-peso coins: " + moneyBox.getNum20Coin() + "\n" +
                            "No. of 10-peso coins: " + moneyBox.getNum10Coin() + "\n" +
                            "No. of 5-peso coins:  " + moneyBox.getNum5Coin() + "\n" +
                            "No. of 1-peso coins:  " + moneyBox.getNum1Coin() + "\n" +
                            "Total: " + moneyBox.computeTotalAmount();

                    replenishMoneyView.setDisplayTextArea(coinsString);
                }
            });

            // Enter
            replenishMoneyView.setEnterBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    int rep20Coin = Integer.parseInt(replenishMoneyView.getCoin20BTF());
                    int rep10Coin = Integer.parseInt(replenishMoneyView.getCoin10BTF());
                    int rep5Coin  = Integer.parseInt(replenishMoneyView.getCoin5BTF());
                    int rep1Coin  = Integer.parseInt(replenishMoneyView.getCoin1BTF());

                    regularVM.getMoneyBox().addCoins(rep20Coin, rep10Coin, rep5Coin, rep1Coin);
                    JOptionPane.showMessageDialog(null, "Money Box has successfully been replenished", "Money Box Replenished!", JOptionPane.PLAIN_MESSAGE);
                    replenishMoneyView.clearAllTextFlds();
                }
            });


            // Back
            replenishMoneyView.setBackBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    replenishMoneyView.dispose();
                    openTestMaintenance();
                }
            });

        } else if (VMType.equalsIgnoreCase("Special")) {
            // If the instantiated VM is Special

            // View
            replenishMoneyView.setViewBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    Cash moneyBox = specialVM.getMoneyBox();

                    String coinsString = "";

                    coinsString += "No. of 20-peso coins: " + moneyBox.getNum20Coin() + "\n" +
                            "No. of 10-peso coins: " + moneyBox.getNum10Coin() + "\n" +
                            "No. of 5-peso coins:  " + moneyBox.getNum5Coin() + "\n" +
                            "No. of 1-peso coins:  " + moneyBox.getNum1Coin() + "\n" +
                            "Total: " + moneyBox.computeTotalAmount();

                    replenishMoneyView.setDisplayTextArea(coinsString);
                }
            });

            // Enter
            replenishMoneyView.setEnterBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    int rep20Coin = Integer.parseInt(replenishMoneyView.getCoin20BTF());
                    int rep10Coin = Integer.parseInt(replenishMoneyView.getCoin10BTF());
                    int rep5Coin  = Integer.parseInt(replenishMoneyView.getCoin5BTF());
                    int rep1Coin  = Integer.parseInt(replenishMoneyView.getCoin1BTF());

                    specialVM.getMoneyBox().addCoins(rep20Coin, rep10Coin, rep5Coin, rep1Coin);
                    JOptionPane.showMessageDialog(null, "Money Box has successfully been replenished", "Money Box Replenished!", JOptionPane.PLAIN_MESSAGE);
                    replenishMoneyView.clearAllTextFlds();
                }
            });


            // Back
            replenishMoneyView.setBackBtnListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    replenishMoneyView.dispose();
                    openTestMaintenance();
                }
            });
        }
    }

    // PRINT TRANSACTIONS
    public void openPrintTransactions (String VMType) {

        if (VMType.equalsIgnoreCase("Regular")) {
            // If the instantiated VM is Regular

            // Print Ending Inventory
            JOptionPane.showMessageDialog(null, regularVM.printEndingInventory(), "Ending Inventory Printed!", JOptionPane.PLAIN_MESSAGE);
            // Print Starting Inventory
            JOptionPane.showMessageDialog(null, regularVM.printStartingInventory(), "Starting Inventory Printed!", JOptionPane.PLAIN_MESSAGE);
            // Print Transaction Log
            JOptionPane.showMessageDialog(null, regularVM.printTransactionLog(), "Transaction Summary Printed!", JOptionPane.PLAIN_MESSAGE);

        } else if (VMType.equalsIgnoreCase("Special")) {
            // If the instantiated VM is Special

            // Print Ending Inventory
            JOptionPane.showMessageDialog(null, specialVM.printEndingInventory(), "Ending Inventory Printed!", JOptionPane.PLAIN_MESSAGE);
            // Print Starting Inventory
            JOptionPane.showMessageDialog(null, specialVM.printStartingInventory(), "Starting Inventory Printed!", JOptionPane.PLAIN_MESSAGE);
            // Print Transaction Log
            JOptionPane.showMessageDialog(null, specialVM.printTransactionLog(), "Transaction Summary Printed!", JOptionPane.PLAIN_MESSAGE);

        }
    }
}

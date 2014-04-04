package main.Form.Forms;

import main.Form.IForm;

import java.util.HashMap;

public class BaseForm implements IForm {
    private HashMap<String, String> formMap = new HashMap<>();
    public HashMap<String, String> form() {
        return formMap;
    }
}

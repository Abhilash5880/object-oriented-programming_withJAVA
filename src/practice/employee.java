package practice;

class employee {
    int  id;
    String name;
    String address;

    employee(int id_no, String emp_name, String emp_address) {
        this.id = id_no;
        this.name = emp_name;
        this.address = emp_address;
    }

    employee(employee rival)
    {
        this.id = rival.id;
        this.name = rival.name;
        this.address = rival.address;
    }
}


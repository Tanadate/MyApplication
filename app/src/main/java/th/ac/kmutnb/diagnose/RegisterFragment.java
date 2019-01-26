package th.ac.kmutnb.diagnose;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
//    Explicit
    private String nameString,surnameString,genderString,heightString,wightString,ageString,userString, passwordString; //ประกาศตัวแปร
    private boolean genderBool = true,heightABoolean = true,weightABoolean=true, ageABoolean = true; //ประกาศตัวแปรแบบ Boolean

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createToolbar();

//        about gender

        RadioGroup radioGroup = getView().findViewById(R.id.ragGender);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                genderBool = false; //เมื่อมีการเลือกเพศจะทำให้ตัวแปรเป็น False
                switch (checkedId) {
                    case R.id.radMale:
                        genderString = "Male";
                        break;
                    case R.id.radFemale:
                        genderString = "Female";
                        break;
                }
            }
        });


        //        About Height

        Spinner heightSpinner = getView().findViewById(R.id.spnHeight); //ตั้งค่าตัวแปร
        final String[] heightStrings = {"Please Choose Height","Below 150","151-170","Over 170"}; //โชว์บน Spinner ต้องเป็นตัวแปรอเรย์
        ArrayAdapter<String> heightStringArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, heightStrings);
        heightSpinner.setAdapter(heightStringArrayAdapter);
        heightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                heightString = heightStrings[position];
                if (position == 0) {
                    heightABoolean = true;
                } else {
                    heightABoolean = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // Main Method

    private void createToolbar() {
        //        Create Toobar

        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.new_register)); //ทำให้ตรงtoolbar เปลี่ยนชื่อเป็น New register
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true); //สร้างปุ่มย้อนกลับ
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true); //ทำให้ปุ่มย้อนกลับกดได้
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack(); //กดกลับไปหน้าที่ตัวเองมา
            }
        });
        setHasOptionsMenu(true); //ใช้เมนู
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemUpload) {
            checkValue(); //เป็น method ที่เช็คว่ากรอกข้อมูลครบไหม
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkValue() {

//        Get Value From EditText
        EditText nameEditText = getView().findViewById(R.id.edtName);
        EditText surnameEditText = getView().findViewById(R.id.edtSurname);
        EditText userEditText = getView().findViewById(R.id.edtUser);
        EditText passwordEditText = getView().findViewById(R.id.edtPassword);

        nameString = nameEditText.getText().toString().trim(); //ตัดช่องว่างออก
        surnameString = surnameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        MyAlert myAlert = new MyAlert(getActivity()); //ถ้าต้องการใช้ Class ภายนอกต้องสร้าง object เสมอ และสิ่งนี้ทำการ Contect ด้วย


//เช็คความว่างเปล่า,gender,height,weight,age

        if (nameString.isEmpty() || surnameString.isEmpty() || userString.isEmpty() || passwordString.isEmpty()) {

//Have space

            myAlert.normalDialog("Have Space","Please Fill Every Blank");

        } else if (genderBool) {

//Non Choose Gender

            myAlert.normalDialog("Non Choose Gender", "Please Choose Male or Female");
        } else if (heightABoolean) {
            myAlert.normalDialog("Non Choose Height", "Please Choose Height");
        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater); //Method ที่ใช้ในการสร้างเมนู
        inflater.inflate(R.menu.menu_register, menu); //ให้เมนูขึ้นมา
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

}

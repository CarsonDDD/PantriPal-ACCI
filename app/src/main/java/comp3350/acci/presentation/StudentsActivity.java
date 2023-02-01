package comp3350.acci.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import comp3350.acci.R;
import comp3350.acci.business.AccessStudents;
import comp3350.acci.objects.Student;

public class StudentsActivity extends Activity {

    private AccessStudents accessStudents;
    private List<Student> studentList;
    private ArrayAdapter<Student> studentArrayAdapter;
    private int selectedStudentPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        accessStudents = new AccessStudents();

        try
        {
            studentList = accessStudents.getStudents();
            studentArrayAdapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, studentList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(studentList.get(position).getStudentID() + ": " + studentList.get(position).getStudentName());
                    text2.setText(studentList.get(position).getStudentAddress());

                    return view;
                }
            };

            final ListView listView = (ListView)findViewById(R.id.listStudents);
            listView.setAdapter(studentArrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Button updateButton = (Button)findViewById(R.id.buttonStudentUpdate);
                    Button deleteButton = (Button)findViewById(R.id.buttonStudentDelete);

                    if (position == selectedStudentPosition) {
                        listView.setItemChecked(position, false);
                        updateButton.setEnabled(false);
                        deleteButton.setEnabled(false);
                        selectedStudentPosition = -1;
                    } else {
                        listView.setItemChecked(position, true);
                        updateButton.setEnabled(true);
                        deleteButton.setEnabled(true);
                        selectedStudentPosition = position;
                        selectStudentAtPosition(position);
                    }
                }
            });
            
            final EditText editStudentID = (EditText)findViewById(R.id.editStudentID);
            final Button buttonStudentCourses = (Button)findViewById(R.id.buttonStudentCourses);
            editStudentID.setEnabled(false);
            editStudentID.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {}

				@Override
				public void afterTextChanged(Editable s) {
					buttonStudentCourses.setEnabled(editStudentID.getText().toString().length() > 0);
				}
            });
        }
        catch (final Exception e)
        {
            Messages.fatalError(this, e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_students, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void selectStudentAtPosition(int position) {
        Student selected = studentArrayAdapter.getItem(position);

        EditText editID = (EditText)findViewById(R.id.editStudentID);
        EditText editName = (EditText)findViewById(R.id.editStudentName);
        EditText editAddress = (EditText)findViewById(R.id.editStudentAddress);

        editID.setText(selected.getStudentID());
        editName.setText(selected.getStudentName());
        editAddress.setText(selected.getStudentAddress());
    }

    public void buttonStudentCoursesOnClick(View v) {
        EditText editID = (EditText)findViewById(R.id.editStudentID);
        String studentID = editID.getText().toString();

        Intent scIntent = new Intent(StudentsActivity.this, StudentCoursesActivity.class);
        Bundle b = new Bundle();
        b.putString("studentID", studentID);
        scIntent.putExtras(b);
        StudentsActivity.this.startActivity(scIntent);
    }

    public void buttonStudentCreateOnClick(View v) {
        Student student = createStudentFromEditText();
        String result;

        result = validateStudentData(student, true);
        if (result == null) {
            try
            {
                student = accessStudents.insertStudent(student);
                if (result == null) {
                    studentList = accessStudents.getStudents();
                    studentArrayAdapter.notifyDataSetChanged();
                    int pos = studentList.indexOf(student);
                    if (pos >= 0) {
                        ListView listView = (ListView) findViewById(R.id.listStudents);
                        listView.setSelection(pos);
                    }
                }
            }
            catch(final Exception e)
            {
                Messages.fatalError(this, e.getMessage());
            }
        } else {
        	Messages.warning(this, result);
        }
    }

    public void buttonStudentUpdateOnClick(View v) {
        Student student = createStudentFromEditText();
        String result;

        result = validateStudentData(student, false);
        if (result == null) {
            try {
                student = accessStudents.updateStudent(student);
                if (result == null) {
                    studentList = accessStudents.getStudents();
                    studentArrayAdapter.notifyDataSetChanged();
                    int pos = studentList.indexOf(student);
                    if (pos >= 0) {
                        ListView listView = (ListView) findViewById(R.id.listStudents);
                        listView.setSelection(pos);
                    }
                }
            } catch (final Exception e) {
                Messages.fatalError(this, e.getMessage());
            }
        } else {
        	Messages.warning(this, result);
        }
    }

    public void buttonStudentDeleteOnClick(View v) {
        Student student = createStudentFromEditText();

        try
        {
            accessStudents.deleteStudent(student);
            int pos = studentList.indexOf(student);
            if (pos >= 0) {
                ListView listView = (ListView) findViewById(R.id.listStudents);
                listView.setSelection(pos);
            }
            studentList = accessStudents.getStudents();
            studentArrayAdapter.notifyDataSetChanged();
        }
        catch (final Exception e)
        {
            Messages.warning(this, e.getMessage());
        }
    }

    private Student createStudentFromEditText() {
        EditText editID = (EditText)findViewById(R.id.editStudentID);
        EditText editName = (EditText)findViewById(R.id.editStudentName);
        EditText editAddress = (EditText)findViewById(R.id.editStudentAddress);

        Student student = new Student(editID.getText().toString(), editName.getText().toString(), editAddress.getText().toString());

        return student;
    }

    private String validateStudentData(Student student, boolean isNewStudent) {
        if (student.getStudentID().length() == 0) {
            return "Student ID required";
        }

        if (student.getStudentName().length() == 0) {
            return "Student name required";
        }

        if (isNewStudent && accessStudents.getRandom(student.getStudentID()) != null) {
            return "Student ID " + student.getStudentID() + " already exists.";
        }

        return null;
    }
}

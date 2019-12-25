package com.example.hasannaseer.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import com.example.hasannaseer.quizapp.QuizContract.*;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyAwesomeQuiz5.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
        fillQuestionsTable1();
        fillQuestionTable2();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Programming");
        addCategory(c1);
        Category c2 = new Category("Arabic");
        addCategory(c2);
        Category c3 = new Category("Math");
        addCategory(c3);
    }

    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("l1=[1,2,3,4] \n \t l2=[1,3,6,7,null,null,null,null] ",
                "l2=[1,1,3,3,4,5,4]", "l2=[1,1,2,3,3,4,6,7]", "l1+2=[1,2,3,4,null,null,null]", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q1);
       /* Question q2 = new Question("Translate GOOD MORNING in Arabic: ",
                "مساء الخير", "صباح الخير", "سلام عليكم", 2,
                Question.DIFFICULTY_HARD, Category.ARABIC);
        addQuestion(q2); */
        Question q5 = new Question("Non existing, Easy: A is correct",
                "A", "B", "C", 1,
                Question.DIFFICULTY_EASY, 4);
        addQuestion(q5);
        Question q6 = new Question("Non existing, Medium: B is correct",
                "A", "B", "C", 2,
                Question.DIFFICULTY_MEDIUM, 5);
        addQuestion(q6);
        Question q7 = new Question("input = [[1,2,3], [9, 0], [5], [-4,-5,-2,-3,-1]]; ",
                "Output: [1,9,5,-4,2,0,-5,3,-2,-3,-1] ", "Output: [1,5,9,2,-4,0,-5,3,-2,-1,-3]", "Output: [-5,-4,-3,-2,-1,0,1,2,3,5,9]", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q7);
        Question q8 = new Question("You have two sorted arrays, where each element is an interval. Now, merge the two array, overlapping intervals can be merged as a single one. Inputs:\n List 1 [1,2] , [3,9] " +
                "\n List 2 [4,5], [8, 10], [11,12] \n ",
                "Output: [1,9,5,-4,2,0,-5,3,-2,-3,-1] ", "Output: [1,5,9,2,-4,0,-5,3,-2,-1,-3]", "Output: [-5,-4,-3,-2,-1,0,1,2,3,5,9]", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q8);
        Question q9 = new Question("∫x^(5) + 3x dx",
                "1/5 x^5 + 3/2 x^2 +C", "1/6 x^6 + 3/2 x^2 +C", "5x^4 + 3 +C", 2,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        addQuestion(q9);
      /* Question q10 = new Question("My name is...",
                "اِسْمِي", "میرا نام ہے", "شُكْراً", 1,
                Question.DIFFICULTY_MEDIUM, Category.ARABIC);
        addQuestion(q10);
        Question q11 = new Question("What’s your name? (spoken to female)",
                "اِسْمِي", "اسمي هو", "اِسْمِك إِيهْ؟", 3,
                Question.DIFFICULTY_HARD, Category.ARABIC);
        addQuestion(q11);
        Question q12 = new Question("Translate in Arabic: Thank you:",
                "Jazakalan", "Shukran", "Habibi", 3,
                Question.DIFFICULTY_EASY, Category.ARABIC);
        addQuestion(q12);
        Question q13 = new Question("Translate in Arabic: I'm from Atlanta in Georgia",
                "Ana Min Atlanta Fi Georgia", "Ana Fi Georgia Min Atlanta", "Ana Georgia Fi Atlanta", 1,
                Question.DIFFICULTY_MEDIUM, Category.ARABIC);
        addQuestion(q13);
       Question q14 = new Question("Translate in Arabic: Yes",
                "Ana", "La", "Na3m", 3,
                Question.DIFFICULTY_EASY, Category.ARABIC);
        addQuestion(q14);
        Question q15 = new Question("Translate in Arabic: No",
                "La", "Ana", "Laan", 1,
                Question.DIFFICULTY_EASY, Category.ARABIC);
        addQuestion(q15);
        Question q16 = new Question("Translate in Arabic: Good morning!",
                "Sabah al khair", "Sabah al noor", "Aslam Alikiykum", 1,
                Question.DIFFICULTY_EASY, Category.ARABIC);
        addQuestion(q16);
        Question q17 = new Question("Translate in Arabic: I speak English!",
                "Al'iinjlizia", "Urdu", "Anglish", 1,
                Question.DIFFICULTY_EASY, Category.ARABIC);
        addQuestion(q17);
        Question q18 = new Question("Translate in Arabic: Good bye (Male)!",
                "Ma3 Alkair", "Ma3 elslamaa", "Allah e Slamak", 2,
                Question.DIFFICULTY_EASY, Category.ARABIC);
        addQuestion(q18); */
    }

    private void fillQuestionsTable1(){
        Question q1 = new Question("Translate in Arabic: Good bye (Male)!",
                "Ma3 Alkair", "Ma3 elslamaa", "Allah e Slamak", 2,
                Question.DIFFICULTY_EASY, Category.ARABIC);
        addQuestion(q1);
        Question q2 = new Question("Translate in Arabic: Yes",
                "Ana", "La", "Na3m", 3,
                Question.DIFFICULTY_EASY, Category.ARABIC);
        addQuestion(q2);
        Question q3 = new Question("Translate in Arabic: No",
                "La", "Ana", "Laan", 1,
                Question.DIFFICULTY_EASY, Category.ARABIC);
        addQuestion(q3);
        Question q4 = new Question("Translate in Arabic: Good morning!",
                "Sabah al khair", "Sabah al noor", "Aslam Alikiykum", 1,
                Question.DIFFICULTY_EASY, Category.ARABIC);
        addQuestion(q4);
        Question q5 = new Question("Translate in Arabic: I speak English!",
                "Al'iinjlizia", "Urdu", "Anglish", 1,
                Question.DIFFICULTY_EASY, Category.ARABIC);
        addQuestion(q5);
        Question q6 = new Question("Translate in Arabic: Thank you:",
                "Jazakalan", "Shukran", "Habibi", 3,
                Question.DIFFICULTY_EASY, Category.ARABIC);
        addQuestion(q6);
        Question q7 = new Question("Translate GOOD MORNING in Arabic: ",
                "مساء الخير", "صباح الخير", "سلام عليكم", 2,
                Question.DIFFICULTY_HARD, Category.ARABIC);
        addQuestion(q7);
        Question q8 = new Question("Translate in Arabic: I'm from Atlanta in Georgia",
                "Ana Min Atlanta Fi Georgia", "Ana Fi Georgia Min Atlanta", "Ana Georgia Fi Atlanta", 1,
                Question.DIFFICULTY_MEDIUM, Category.ARABIC);
        addQuestion(q8);
        Question q10 = new Question("My name is...",
                "اِسْمِي", "میرا نام ہے", "شُكْراً", 1,
                Question.DIFFICULTY_MEDIUM, Category.ARABIC);
        addQuestion(q10);
        Question q11 = new Question("What’s your name? (spoken to female)",
                "اِسْمِي", "اسمي هو", "اِسْمِك إِيهْ؟", 3,
                Question.DIFFICULTY_HARD, Category.ARABIC);
        addQuestion(q11);
        Question q12 = new Question("Translate in Arabic: Good bye (Male)!",
                "Ma3 Alkair", "Ma3 elslamaa", "Allah e Slamak", 2,
                Question.DIFFICULTY_EASY, Category.ARABIC);
        addQuestion(q12);
        Question q13 = new Question("Translate in Arabic: Good bye (Male)!",
                "باي", "مع السّلامة", "صباح الخير", 2,
                Question.DIFFICULTY_HARD, Category.ARABIC);
        addQuestion(q13);
    }

    private void fillQuestionTable2(){
        Question q1 = new Question("1 * 2 (3-4) + 102 + 10",
                "113", "100", "110", 3,
                Question.DIFFICULTY_EASY, Category.MATH);
        addQuestion(q1);
        Question q2 = new Question("Math, Hard: C is correct",
                "A", "B", "C", 3,
                Question.DIFFICULTY_HARD, Category.MATH);
        addQuestion(q2);
        Question q3 = new Question("Math, Easy: A is correct",
                "A", "B", "C", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        addQuestion(q3);
        Question q4 = new Question("Math, Hard: C is correct",
                "A", "B", "C", 3,
                Question.DIFFICULTY_HARD, Category.MATH);
        addQuestion(q4);
        Question q5 = new Question("5/2 + 2/5",
                "29/10", "52/10", "21/22", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        addQuestion(q5);
        Question q6 = new Question("3 * (2-1) + 6",
                "11", "18", "9", 3,
                Question.DIFFICULTY_EASY, Category.MATH);
        addQuestion(q6);
        Question q7 = new Question("3 / (22-11) + 24",
                "267/11", "289/22", "222/22", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        addQuestion(q7);
        Question q8 = new Question("17 + 22 * 2 - (62-22)",
                "-23", "21", "22", 2,
                Question.DIFFICULTY_EASY, Category.MATH);
        addQuestion(q8);
        Question q9 = new Question("Find the Distance 'd' between the points: (1, 2), (−3, 5)",
                "d = 10", "d = 5", "d = 12", 2,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        addQuestion(q9);
        Question q10 = new Question("Find the Mid-point 'M' between the points:(1/2 , 4) , (3/2 , -1)",
                "M = (2 , 1/2)", "M = (3/2 , 1)", "M = (1 , 3/2)", 1,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        addQuestion(q10);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}

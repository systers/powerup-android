/**
* @desc this class holds functions for updating answers, questions, and avatar
* features. Examples include getAvatarFace() and setCompletedScenario()
*/

package powerup.systers.com.db;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import java.util.List;
import powerup.systers.com.datamodel.Answer;
import powerup.systers.com.datamodel.Question;
import powerup.systers.com.datamodel.Scenario;
import powerup.systers.com.datamodel.SessionHistory;

public class DatabaseHandler extends AbstractDbAdapter {

    public DatabaseHandler(Context ctx) {
        super(ctx);
        ctx.getAssets();
    }

    /**
    * @ desc returns false if the first row cursor is empty, otherwise returns true.
    * @return boolean - if the game is over or not
    */
    public boolean gameOver() {
        String selectQuery = "SELECT  * FROM " + PowerUpContract.ScenarioEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.ScenarioEntry.COLUMN_COMPLETED + " = 0";
        Cursor cursor = sDb.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            return false;
        }
        cursor.close();
        return true;
    }

    /**
    * @desc adds all answer options to an ArrayList.
    * @param answers - the ArrayList to add answers to
    * @param qId - the question ID
    */
    public void getAllAnswer(List<Answer> answers, int qId) {
        String selectQuery = "SELECT  * FROM " + PowerUpContract.AnswerEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.AnswerEntry.COLUMN_QUESTION_ID + " = " + qId;
        Cursor cursor = sDb.rawQuery(selectQuery, null);
        answers.clear();
        if (cursor.moveToFirst()) {
            do {
                Answer ans = new Answer();
                ans.setAnswerId(cursor.getInt(0));
                ans.setQuestionId(cursor.getInt(1));
                ans.setAnswerDescription(cursor.getString(2));
                ans.setNextQuestionId(cursor.getInt(3));
                ans.setPoints(cursor.getInt(4));
                answers.add(ans);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    /**
    * @desc as long as the game is not over, the current question will be returned 
    * (otherwise return null)
    * @return Question - the question of the current session
    */
    public Question getCurrentQuestion() {
        String selectQuery = "SELECT  * FROM " + PowerUpContract.QuestionEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.QuestionEntry.COLUMN_QUESTION_ID + " = "
                + SessionHistory.currQId;
        Cursor cursor = sDb.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            Question question = new Question();
            question.setQuestionId(cursor.getInt(0));
            question.setScenarioId(cursor.getInt(1));
            question.setQuestionDescription(cursor.getString(2));
            return question;
        }
        cursor.close();
        return null;
    }

    /** 
    * @desc as long as the game is not over, the current scenario will be returned 
    * (otherwise return null)
    * @return Scenario - the scenario of the current session
    */
    public Scenario getScenario() {
        String selectQuery = "SELECT  * FROM " + PowerUpContract.ScenarioEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.ScenarioEntry.COLUMN_ID + " = "
                + SessionHistory.currSessionId;
        Cursor cursor = sDb.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            Scenario scene = new Scenario();
            scene.setId(cursor.getInt(0));
            scene.setScenarioName(cursor.getString(1));
            scene.setTimestamp(cursor.getString(2));
            scene.setAsker(cursor.getString(3));
            scene.setAvatar(cursor.getInt(4));
            scene.setFirstQuestionId(cursor.getInt(5));
            scene.setCompleted(cursor.getInt(6));
            scene.setNextScenarioId(cursor.getInt(7));
            scene.setReplayed(cursor.getInt(8));
            return scene;
        }
        cursor.close();
        return null;
    }

    /**
    * @desc simply get a scenario by searching with its ID.
    * @param id - the ID of the scenario you are looking for
    * @return Scenario - the scenario of the passed ID
    */
    public Scenario getScenarioFromID(int id) {
        String selectQuery = "SELECT  * FROM " + PowerUpContract.ScenarioEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.ScenarioEntry.COLUMN_ID + " = "
                + id;
        Cursor cursor = sDb.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            Scenario scene = new Scenario();
            scene.setId(cursor.getInt(0));
            scene.setScenarioName(cursor.getString(1));
            scene.setTimestamp(cursor.getString(2));
            scene.setAsker(cursor.getString(3));
            scene.setAvatar(cursor.getInt(4));
            scene.setFirstQuestionId(cursor.getInt(5));
            scene.setCompleted(cursor.getInt(6));
            scene.setNextScenarioId(cursor.getInt(7));
            scene.setReplayed(cursor.getInt(8));
            return scene;
        }
        cursor.close();
        return null;
    }

    /** 
    * @desc prevents a previously completed scenario from being repeated again. Sets
    * scene ID to the scenario name being passed.
    * @param ScenarioName - the scenario being checked for completion
    * @return boolean - if the scenario has been completed or not
    */
    public boolean setSessionId(CharSequence ScenarioName) {
        String selectQuery = "SELECT  * FROM " + PowerUpContract.ScenarioEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.ScenarioEntry.COLUMN_SCENARIO_NAME + " = "
                + "\"" + ScenarioName + "\"";
        Cursor cursor = sDb.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            // If the scene is already completed
            if (cursor.getInt(6) == 1) {
                return false;
            }
            SessionHistory.currSessionId = cursor.getInt(0);
            SessionHistory.currQId = cursor.getInt(5);
            return true;
        }
        cursor.close();
        // Scenario not Found
        return false;
    }

    /**
    * @desc records completed scenarios in the database.
    * @param id - the scenario ID to record completion
    */
    public void setCompletedScenario(int id) {
        String updateQuery = "UPDATE  " + PowerUpContract.ScenarioEntry.TABLE_NAME +
                " SET " + PowerUpContract.ScenarioEntry.COLUMN_COMPLETED + "=1" +
                " WHERE " + PowerUpContract.ScenarioEntry.COLUMN_ID + " = " + id;
        sDb.execSQL(updateQuery);
        String selectQuery = "SELECT  * FROM " + PowerUpContract.ScenarioEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.ScenarioEntry.COLUMN_ID + " = "
                + id;
        Cursor cursor = sDb.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            Log.i("Completed : ", String.valueOf(cursor.getInt(6)));
        }
        cursor.close();
    }

    /**
    * @desc overloaded version of setCompletedScenario function. Uses scenario
    * name to set completion.
    * @param ScenarioName - the name of scenario to record completion
    */
    public void setCompletedScenario(CharSequence ScenarioName) {
        String updateQuery = "UPDATE  " + PowerUpContract.ScenarioEntry.TABLE_NAME +
                " SET " + PowerUpContract.ScenarioEntry.COLUMN_COMPLETED + "=1" +
                " WHERE " + PowerUpContract.ScenarioEntry.COLUMN_SCENARIO_NAME + " = " +
                "\"" + ScenarioName + "\"";
        sDb.execSQL(updateQuery);
    }

    /**
    * @desc records "replayed" variable for a scenario into the database.
    * @param ScenarioName - the name of scenario to record "replayed"
    */
    public void setReplayedScenario(CharSequence ScenarioName) {
        String updateQuery = "UPDATE  " + PowerUpContract.ScenarioEntry.TABLE_NAME +
                " SET " + PowerUpContract.ScenarioEntry.COLUMN_REPLAYED + "=1" +
                " WHERE " + PowerUpContract.ScenarioEntry.COLUMN_SCENARIO_NAME + " = " +
                "\"" + ScenarioName + "\"";
        sDb.execSQL(updateQuery);
    }

    /**
    * @desc get the current avatar face to display.
    * @return Integer - the number of the current face. Default is 1.
    */
    public int getAvatarFace() {
        String query = "Select * from " + PowerUpContract.AvatarEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.AvatarEntry.COLUMN_ID + " = 1";
        Cursor cursor = sDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(1);
        }
        cursor.close();
        return 1;
    }

    /**
    * @desc change the avatar's face.
    * @param face - the number of the requested face
    */
    public void setAvatarFace(int face) {
        String query = "UPDATE " + PowerUpContract.AvatarEntry.TABLE_NAME +
                " SET " + PowerUpContract.AvatarEntry.COLUMN_FACE + " = " + face +
                " WHERE " + PowerUpContract.AvatarEntry.COLUMN_ID + " = 1";
        sDb.execSQL(query);
    }

    /**
    * @desc get the current avatar eye to display.
    * @return int - the number of the current eye. Default is 1.
    */
    public int getAvatarEye() {
        String query = "Select * from " + PowerUpContract.AvatarEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.AvatarEntry.COLUMN_ID + " = 1";
        Cursor cursor = sDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(4);
        }
        cursor.close();
        return 1;
    }

    /** 
    * @desc change the avatar's eyes.
    * @param eye - the number of the requested eye
    */
    public void setAvatarEye(int eye) {
        String query = "UPDATE " + PowerUpContract.AvatarEntry.TABLE_NAME +
                " SET " + PowerUpContract.AvatarEntry.COLUMN_EYES + " = " + eye +
                " WHERE " + PowerUpContract.AvatarEntry.COLUMN_ID + " = 1";
        sDb.execSQL(query);
    }

    /**
    * @desc get the current avatar clothing to display.
    * @return int - the number of the current clothes. Default is 1.
    */
    public int getAvatarCloth() {
        String query = "Select * from " + PowerUpContract.AvatarEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.AvatarEntry.COLUMN_ID + " = 1";
        Cursor cursor = sDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(2);
        }
        cursor.close();
        return 1;
    }

    /**
    * @desc change the avatar's clothes.
    * @param cloth - the number of the requested clothing
    */
    public void setAvatarCloth(int cloth) {
        String query = "UPDATE " + PowerUpContract.AvatarEntry.TABLE_NAME +
                " SET " + PowerUpContract.AvatarEntry.COLUMN_CLOTHES + " = " + cloth +
                " WHERE " + PowerUpContract.AvatarEntry.COLUMN_ID + " = 1";
        sDb.execSQL(query);
    }

    /**
    * @desc get the current avatar hair to display.
    * @return int - the number of the current hair. Default is 1.
    */
    public int getAvatarHair() {
        String query = "Select * from " + PowerUpContract.AvatarEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.AvatarEntry.COLUMN_ID + " = 1";
        Cursor cursor = sDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(3);
        }
        cursor.close();
        return 1;
    }

    /**
    * @desc change the avatar's hair.
    * @param hair - the number of the requested hair
    */
    public void setAvatarHair(int hair) {
        String query = "UPDATE " + PowerUpContract.AvatarEntry.TABLE_NAME +
                " SET " + PowerUpContract.AvatarEntry.COLUMN_HAIR + " = " + hair +
                " WHERE " + PowerUpContract.AvatarEntry.COLUMN_ID + " = 1";
        sDb.execSQL(query);
    }

    /**
    * @desc get the current bag to display.
    * @return int - the number of the current bag. Default is 0.
    */
    public int getAvatarBag() {
        String query = "Select * from " + PowerUpContract.AvatarEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.AvatarEntry.COLUMN_ID + " = 1";
        Cursor cursor = sDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(5);
        }
        cursor.close();
        return 0;
    }

    /**
    * @desc change the avatar's bag.
    * @param bag - the number of the requested bag
    */
    public void setAvatarBag(int bag) {
        String query = "UPDATE " + PowerUpContract.AvatarEntry.TABLE_NAME +
                " SET " + PowerUpContract.AvatarEntry.COLUMN_BAG + " = " + bag +
                " WHERE " + PowerUpContract.AvatarEntry.COLUMN_ID + " = 1";
        sDb.execSQL(query);
    }

    /**
    * @desc get the current glasses to display.
    * @return int - the number of the current glasses. Default is 0.
    */
    public int getAvatarGlasses() {
        String query = "Select * from " + PowerUpContract.AvatarEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.AvatarEntry.COLUMN_ID + " = 1";
        Cursor cursor = sDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(6);
        }
        cursor.close();
        return 0;
    }

    /**
    * @desc change the avatar's glasses.
    * @param glasses - the number of the requested glasses
    */
    public void setAvatarGlasses(int glasses) {
        String query = "UPDATE " + PowerUpContract.AvatarEntry.TABLE_NAME +
                " SET " + PowerUpContract.AvatarEntry.COLUMN_GLASSES + " = " + glasses +
                " WHERE " + PowerUpContract.AvatarEntry.COLUMN_ID + " = 1";
        sDb.execSQL(query);
    }

    /**
    * @desc get the current hat to display.
    * @return int - the number of the current hat. Default is 0.
    */
    public int getAvatarHat() {
        String query = "Select * from " + PowerUpContract.AvatarEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.AvatarEntry.COLUMN_ID + " = 1";
        Cursor cursor = sDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(7);
        }
        cursor.close();
        return 0;
    }

    /**
    * @desc change the avatar's hat.
    * @param hat - the number of the requested hat
    */
    public void setAvatarHat(int hat) {
        String query = "UPDATE " + PowerUpContract.AvatarEntry.TABLE_NAME +
                " SET " + PowerUpContract.AvatarEntry.COLUMN_HAT + " = " + hat +
                " WHERE " + PowerUpContract.AvatarEntry.COLUMN_ID + " = 1";
        sDb.execSQL(query);
    }

    /**
    * @desc get the current necklace to display.
    * @return int - the number of the current necklace. Default is 0.
    */
    public int getAvatarNecklace() {
        String query = "Select * from " + PowerUpContract.AvatarEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.AvatarEntry.COLUMN_ID + " = 1";
        Cursor cursor = sDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(8);
        }
        cursor.close();
        return 0;
    }

    /**
    * @desc change the avatar's necklace.
    * @param necklace - the number of the requested necklace
    */
    public void setAvatarNecklace(int necklace) {
        String query = "UPDATE " + PowerUpContract.AvatarEntry.TABLE_NAME +
                " SET " + PowerUpContract.AvatarEntry.COLUMN_NECKLACE + " = " + necklace +
                " WHERE " + PowerUpContract.AvatarEntry.COLUMN_ID + " = 1";
        sDb.execSQL(query);
    }

    /**
    * @desc get the current progress of healing to display in bar.
    * @return int - the current healing score. Default is 1.
    */
    public int getHealing() {
        String query = "Select * from " + PowerUpContract.PointEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.PointEntry.COLUMN_ID + " = 1";
        Cursor cursor = sDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(3);
        }
        cursor.close();
        return 1;
    }

    /**
    * @desc change healing score.
    * @param healing - the requested score to change healing to
    */
    public void setHealing(int healing) {
        String query = "UPDATE " + PowerUpContract.PointEntry.TABLE_NAME +
                " SET " + PowerUpContract.PointEntry.COLUMN_HEALING + " = " + healing +
                " WHERE " + PowerUpContract.PointEntry.COLUMN_ID + " = 1";
        sDb.execSQL(query);
        Log.i("heal", getHealing() + " ");
    }

    /**
    * @desc get the current progress of strength to display in bar.
    * @return int - the current strength score. Default is 1.
    */
    public int getStrength() {
        String query = "Select * from " + PowerUpContract.PointEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.PointEntry.COLUMN_ID + " = 1";
        Cursor cursor = sDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(1);
        }
        cursor.close();
        return 1;
    }

    /**
    * @desc change strength score.
    * @param strength - the requested score to change strength to
    */
    public void setStrength(int strength) {
        String query = "UPDATE " + PowerUpContract.PointEntry.TABLE_NAME +
                " SET " + PowerUpContract.PointEntry.COLUMN_STRENGTH + " = " + strength +
                " WHERE " + PowerUpContract.PointEntry.COLUMN_ID + " = 1";
        sDb.execSQL(query);
    }

    /**
    * @desc get the current progress of telepathy to display in bar.
    * @return int - the current telepathy score. Default is 1.
    */
    public int getTelepathy() {
        String query = "Select * from " + PowerUpContract.PointEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.PointEntry.COLUMN_ID + " = 1";
        Cursor cursor = sDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(4);
        }
        cursor.close();
        return 1;
    }

    /**
    * @desc change telepathy score.
    * @param telepathy - the requested score to change telepathy to
    */
    public void setTelepathy(int telepathy) {
        String query = "UPDATE " + PowerUpContract.PointEntry.TABLE_NAME +
                " SET " + PowerUpContract.PointEntry.COLUMN_TELEPATHY + " = " + telepathy +
                " WHERE " + PowerUpContract.PointEntry.COLUMN_ID + " = 1";
        sDb.execSQL(query);
    }

    /**
    * @desc get the current progress of invisibility to display in bar.
    * @return int - the current invisibility score. Default is 1.
    */
    public int getInvisibility() {
        String query = "Select * from " + PowerUpContract.PointEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.PointEntry.COLUMN_ID + " = 1";
        Cursor cursor = sDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(2);
        }
        cursor.close();
        return 1;
    }

    /**
    * @desc change invisibility score.
    * @param invisibility - the requested score to change invisibility to
    */
    public void setInvisibility(int invisibility) {
        String query = "UPDATE " + PowerUpContract.PointEntry.TABLE_NAME +
                " SET " + PowerUpContract.PointEntry.COLUMN_INVISIBILITY + " = " + invisibility +
                " WHERE " + PowerUpContract.PointEntry.COLUMN_ID + " = 1";
        sDb.execSQL(query);
    }

    /**
    * @desc get the cost for a piece of clothing.
    * @param id - the ID of the requested clothing
    * @return int - the points needed to purchase the clothing. Default is 1.
    */
    public int getPointsClothes(int id) {
        String query = "Select * from " + PowerUpContract.ClothesEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.ClothesEntry.COLUMN_ID + " = " + id;
        Cursor cursor = sDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(2);
        }
        cursor.close();
        return 1;
    }

    /**
    * @desc get the cost for hair.
    * @param id - the ID of the requested hair
    * @return int - the points needed to purchase the hair. Default is 1.
    */
    public int getPointsHair(int id) {
        String query = "Select * from " + PowerUpContract.HairEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.HairEntry.COLUMN_ID + " = " + id;
        Cursor cursor = sDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(2);
        }
        cursor.close();
        return 1;
    }

    /**
    * @desc get the cost for an accessory.
    * @param id - the ID of the requested accessory
    * @return int - the points needed to purchase the accessory. Default is 1.
    */
    public int getPointsAccessories(int id) {
        String query = "Select * from " + PowerUpContract.AccessoryEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.AccessoryEntry.COLUMN_ID + " = " + id;
        Cursor cursor = sDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(2);
        }
        cursor.close();
        return 1;
    }

    /**
    * @desc confirms that a piece of clothing has been bought to lay "purchased" text
    * on top.
    * @param id - the ID of the clothing to check
    * @return int - if the clothes have been purchased or not (return 1 if purchased)
    */
    public int getPurchasedClothes(int id) {
        String query = "Select * from " + PowerUpContract.ClothesEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.ClothesEntry.COLUMN_ID + " = " + id;
        Cursor cursor = sDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(3);
        }
        cursor.close();
        return 1;
    }

    /**
    * @desc updates a piece of clothing to know that it has been purchased.
    * @param id - the ID of the clothing to record purchased status
    */
    public void setPurchasedClothes(int id) {
        String query = "UPDATE " + PowerUpContract.ClothesEntry.TABLE_NAME +
                " SET " + PowerUpContract.ClothesEntry.COLUMN_PURCHASED + " = 1"  +
                " WHERE " + PowerUpContract.ClothesEntry.COLUMN_ID + " = " + id;
        sDb.execSQL(query);
    }

    /**
    * @desc confirms that a hair has been bought to lay "purchased" text on top.
    * @param id - the ID of the hair to check
    * @return int - if the hair been purchased or not (return 1 if purchased)
    */
    public int getPurchasedHair(int id) {
        String query = "Select * from " + PowerUpContract.HairEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.HairEntry.COLUMN_ID + " = " + id;
        Cursor cursor = sDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(3);
        }
        cursor.close();
        return 1;
    }

    /**
    * @desc updates a hair to know that it has been purchased.
    * @param id - the ID of the hair to record purchased status
    */
    public void setPurchasedHair(int id) {
        String query = "UPDATE " + PowerUpContract.HairEntry.TABLE_NAME +
                " SET " + PowerUpContract.HairEntry.COLUMN_PURCHASED + " = 1"  +
                " WHERE " + PowerUpContract.HairEntry.COLUMN_ID + " = " + id;
        sDb.execSQL(query);
    }

    /**
    * @desc confirms that an accessory has been bought to lay "purchased" text on top.
    * @param id - the ID of the accessory to check
    * @return int - if the accessory been purchased or not (return 1 if purchased)
    */
    public int getPurchasedAccessories(int id) {
        String query = "Select * from " + PowerUpContract.AccessoryEntry.TABLE_NAME +
                " WHERE " + PowerUpContract.AccessoryEntry.COLUMN_ID + " = " + id;
        Cursor cursor = sDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(3);
        }
        cursor.close();
        return 1;
    }

    /**
    * @desc updates an accessory to know that it has been purchased.
    * @param id - the ID of the accessory to record purchased status
    */
    public void setPurchasedAccessories(int id) {
        String query = "UPDATE " + PowerUpContract.AccessoryEntry.TABLE_NAME +
                " SET " + PowerUpContract.AccessoryEntry.COLUMN_PURCHASED + " = 1"  +
                " WHERE " + PowerUpContract.AccessoryEntry.COLUMN_ID + " = " + id;
        sDb.execSQL(query);
    }

    /**
    * @desc sets scenarios back to default (not complete).
    */
    public void updateComplete() {
        String query = "UPDATE " + PowerUpContract.ScenarioEntry.TABLE_NAME +
                " SET " + PowerUpContract.ScenarioEntry.COLUMN_COMPLETED + " = 0";
        sDb.execSQL(query);
    }

    /**
    * @desc sets scenarios back so that they can be replayed.
    */
    public void updateReplayed() {
        String query = "UPDATE " + PowerUpContract.ScenarioEntry.TABLE_NAME +
                " SET " + PowerUpContract.ScenarioEntry.COLUMN_REPLAYED + " = 0";
        sDb.execSQL(query);
    }
    
    /**
    * @desc sets purchases to default so that nothing has been bought.
    */
    public void resetPurchase() {
        String query = "UPDATE " + PowerUpContract.ClothesEntry.TABLE_NAME +
                " SET " + PowerUpContract.ClothesEntry.COLUMN_PURCHASED + " = 0";
        sDb.execSQL(query);

        String query2 = "UPDATE " + PowerUpContract.HairEntry.TABLE_NAME +
                " SET " + PowerUpContract.HairEntry.COLUMN_PURCHASED + " = 0";
        sDb.execSQL(query2);

        String query3 = "UPDATE " + PowerUpContract.AccessoryEntry.TABLE_NAME +
                " SET " + PowerUpContract.AccessoryEntry.COLUMN_PURCHASED + " = 0";
        sDb.execSQL(query3);
    }
}

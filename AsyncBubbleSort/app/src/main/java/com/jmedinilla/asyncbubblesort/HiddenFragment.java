package com.jmedinilla.asyncbubblesort;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;

public class HiddenFragment extends Fragment {
    private ITaskCallback taskCallback;
    BubbleNumberTask bubbleNumberTask;

    interface ITaskCallback {
        void onPreExecute();

        void onProgressUpdate(int progress);

        void onCancelled();

        void onPostExecute(Long aVoid);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            taskCallback = (ITaskCallback) activity;
        } catch (ClassCastException exc) {
            throw new ClassCastException(activity.getLocalClassName() + " must implements ITaskCallback interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        taskCallback = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        bubbleNumberTask = new BubbleNumberTask();
        bubbleNumberTask.execute();
    }

    class BubbleNumberTask extends AsyncTask<Void, Integer, Long> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            taskCallback.onPreExecute();
        }

        @Override
        protected Long doInBackground(Void... voids) {
            Long t0 = System.currentTimeMillis();
            int aux;
            for (int i = 0; i < MainActivity.numbers.length - 1; i++) {
                for (int j = 0; j < MainActivity.numbers.length -1; j++) {
                    if (MainActivity.numbers[j] > MainActivity.numbers[j+1])
                    {
                        aux = MainActivity.numbers[j];
                        MainActivity.numbers[j] = MainActivity.numbers[j+1];
                        MainActivity.numbers[j+1] = aux;
                    }
                }

                if(!isCancelled())
                    publishProgress((int)(((i+1)/(float)(MainActivity.numbers.length-1))*100));
                else break;
            }
            Long t1 = System.currentTimeMillis();
            return ((t1 - t0) / 1000);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            taskCallback.onProgressUpdate(values[0]);
        }

        @Override
        protected void onPostExecute(Long aVoid) {
            super.onPostExecute(aVoid);
            taskCallback.onPostExecute(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            taskCallback.onCancelled();
        }
    }
}

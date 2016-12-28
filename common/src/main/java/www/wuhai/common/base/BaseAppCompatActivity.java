package www.wuhai.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Stack;

/**
 * 基类AppCompatActivity
 */
public class BaseAppCompatActivity extends AppCompatActivity{
    private static Stack<Activity> activityStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addActivity(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finishActivity(this);
    }

    /**
     * add Activity 添加Activity到栈
     */
    public static void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * activityStack栈中的activity数量
     * @return
     */
    public static int getActivitySize() {
        return activityStack.size();
    }

    /**
     * get current Activity 获取当前Activity（栈中最后一个压入的）
     */
    public static Activity getCurrentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（栈中最后一个压入的）
     */
    public static void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }


    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }


    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }


    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 清除掉中间的Activity 到制定Activity
     */
    public void finishToActivity(Class<?> cls) {
//        for (int i = 0, size = activityStack.size(); i < size; i++) {
//            if (null != activityStack.get(i)) {
//                Activity act = activityStack.get(i);
//                if(act.getClass().equals(cls)) {
//                    activityStack.get(i).finish();
//                }
//            }
//        }

        /**
         * 对栈的操作
         */
        Activity act = null;
        while(activityStack.isEmpty()){
            act = activityStack.peek();
            if(act.getClass().equals(cls)){
                break;
            }else{
                act = activityStack.pop();
                act.finish();
            }
        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAllActivity();
        } catch (Exception e) {
        }
    }
}

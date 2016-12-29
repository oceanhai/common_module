package www.wuhai.common.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import www.wuhai.common.R;
import www.wuhai.common.widget.slidingfinish.SwipeBackLayout;


/**
 * 想要实现向右滑动删除Activity效果只需要继承SwipeBackActivity即可，如果当前页面含有ViewPager
 * 只需要调用SwipeBackLayout的setViewPager()方法即可----------------没有setViewPager()，不用也行
 *
 */
public class BaseSwipeBackActivity extends BaseAppCompatActivity {
	protected SwipeBackLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if(canSlidingFinish()){
			layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(
					R.layout.layout_swipeback_base, null);
			layout.attachToActivity(this);
		}
	}

	/**
	 * 侧滑开关 需要开启侧滑销毁的activity只需要在继承类里重写此方法 return true
	 * @return
	 */
	public boolean canSlidingFinish(){
		return false;
	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		overridePendingTransition(R.anim.swipeback_base_slide_right_in,
				R.anim.swipeback_base_slide_remain);
	}

	// Press the back button in mobile phone
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(0, R.anim.swipeback_base_slide_right_out);
	}

}

package com.example.androidtest;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * TextView 显示 html 内容
 */
public class HtmlTextViewAct extends Activity implements OnClickListener {

	// String htmlString =
	// \"  <div id=\\"main\\">    <form id=\\"taxlawDetail\\" name=\\"taxlawDetail\\" action=\\"/taxlawDetail.action\\" method=\\"post\\">     <h2>财政部 国家税务总局关于对科研单位取得的技术转让收入免征营业税的通知</h2>     <h3>（1994）财税字第10号</h3>     <dl>      <span id=\\"lawyxx\\">条款失效</span>&nbsp;&nbsp;成文日期：1994-06-03      <br /> 字体：      <a href=\\"javascript:changeFontSize(1);\\">【大】</a> &nbsp;      <a href=\\"javascript:changeFontSize(0);\\">【中】</a> &nbsp;      <a href=\\"javascript:changeFontSize(-1);\\">【小】</a>     </dl>     <div id=\\"content\\">      <div class=\\"TextContentDuanLuo\\">       &nbsp;&nbsp;&nbsp;&nbsp;根据国务院《关于研究财税体制改革方案出台后有关问题的会议纪要》（国阅〔1994〕42号文件）的决定，现就技术转让免征营业税问题通知如下：      </div>      <div class=\\"TextContentDuanLuo\\">       &nbsp;&nbsp;&nbsp;&nbsp;一、为了鼓励技术引进和推广，对科研单位取得的技术转让收入免征营业税。      </div>      <div class=\\"TextContentDuanLuo\\">       &nbsp;&nbsp;&nbsp;&nbsp;二、本通知所说的技术转让，是指有偿转让专利和专利技术的所有权或使用权的行为。      </div>      <div class=\\"TextContentDuanLuo\\">       &nbsp;&nbsp;&nbsp;&nbsp;三、科研单位转让技术，应持各级科委技术市场管理机构出具的技术合同认定登记证明，向主管税务机关提出申请。由主管税务机关审核批准后，方可享受免征营业税照顾。      </div>      <div class=\\"TextContentDuanLuo\\">
	// &nbsp;&nbsp;&nbsp;&nbsp;四、本规定从1994年1月1日起执行。本文下发之前已征收的营业税退还给科研单位。 </div>
	// <br /> </div> </form> </div> \";
	// String htmlString =
	// \"<div id=\\"content\\">      <div class=\\"TextContentDuanLuo\\">       &nbsp;&nbsp;&nbsp;&nbsp;根据国务院《关于研究财税体制改革方案出台后有关问题的会议纪要》（国阅〔1994〕42号文件）的决定，现就技术转让免征营业税问题通知如下：      </div>      <div class=\\"TextContentDuanLuo\\">       &nbsp;&nbsp;&nbsp;&nbsp;一、为了鼓励技术引进和推广，对科研单位取得的技术转让收入免征营业税。      </div>      <div class=\\"TextContentDuanLuo\\">       &nbsp;&nbsp;&nbsp;&nbsp;二、本通知所说的技术转让，是指有偿转让专利和专利技术的所有权或使用权的行为。      </div>      <div class=\\"TextContentDuanLuo\\">       &nbsp;&nbsp;&nbsp;&nbsp;三、科研单位转让技术，应持各级科委技术市场管理机构出具的技术合同认定登记证明，向主管税务机关提出申请。由主管税务机关审核批准后，方可享受免征营业税照顾。      </div>      <div class=\\"TextContentDuanLuo\\">
	// &nbsp;&nbsp;&nbsp;&nbsp;四、本规定从1994年1月1日起执行。本文下发之前已征收的营业税退还给科研单位。 </div>
	// \";
	
	String htmlString = "&#60;div id=\"content\"&#62;      &#60;div class=\"TextContentDuanLuo\"&#62;       &nbsp;&nbsp;&nbsp;&nbsp;根据国务院&#174;关于研究财税体制改革方案出台后有关问题的会议纪要&#175;（国阅〔1994〕42号文件）的决定，现就技术转让免征营业税问题通知如下：      &#60;&#47;div&#62;      &#60;div class=\"TextContentDuanLuo\"&#62;       &nbsp;&nbsp;&nbsp;&nbsp;一、为了鼓励技术引进和推广，对科研单位取得的技术转让收入免征营业税。      &#60;&#47;div&#62;      &#60;div class=\"TextContentDuanLuo\"&#62;       &nbsp;&nbsp;&nbsp;&nbsp;二、本通知所说的技术转让，是指有偿转让专利和专利技术的所有权或使用权的行为。      &#60;&#47;div&#62;      &#60;div class=\"TextContentDuanLuo\"&#62;       &nbsp;&nbsp;&nbsp;&nbsp;三、科研单位转让技术，应持各级科委技术市场管理机构出具的技术合同认定登记证明，向主管税务机关提出申请。由主管税务机关审核批准后，方可享受免征营业税照顾。      &#60;&#47;div&#62;      &#60;div class=\"TextContentDuanLuo\"&#62;       &nbsp;&nbsp;&nbsp;&nbsp;四、本规定从1994年1月1日起执行。本文下发之前已征收的营业税退还给科研单位。      &#60;&#47;div&#62; ";
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		ScrollView scrollView = new ScrollView(this);
		scrollView.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));

		LinearLayout layout = new LinearLayout(this);
		layout.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.setOrientation(LinearLayout.VERTICAL);

		LinearLayout btnLayout = new LinearLayout(this);
		btnLayout.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		Button button1 = new Button(this);
		button1.setId(1);
		button1.setText("大");
		button1.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1));
		button1.setOnClickListener(this);
		btnLayout.addView(button1);

		Button button2 = new Button(this);
		button2.setId(2);
		button2.setText("中");
		button2.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1));
		button2.setOnClickListener(this);
		btnLayout.addView(button2);

		Button button3 = new Button(this);
		button3.setId(3);
		button3.setText("小");
		button3.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1));
		button3.setOnClickListener(this);
		btnLayout.addView(button3);

		layout.addView(btnLayout);

		textView = new TextView(this);
		textView.setLayoutParams(new ViewGroup.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

		textView.setText(Html.fromHtml("<div id=\"content\"><div class=\"TextContentDuanLuo\">       &nbsp;&nbsp;&nbsp;&nbsp;根据国务院《关于研究财税体制改革方案出台后有关问题的会议纪要》（国阅〔1994〕42号文件）的决定，现就技术转让免征营业税问题通知如下：</div>   <div class=\"TextContentDuanLuo\">  	&nbsp;&nbsp;&nbsp;&nbsp;一、为了鼓励技术引进和推广，对科研单位取得的技术转让收入免征营业税。  	</div>   <div class=\"TextContentDuanLuo\"> &nbsp;&nbsp;&nbsp;&nbsp;二、本通知所说的技术转让，是指有偿转让专利和专利技术的所有权或使用权的行为。  	</div>   <div class=\"TextContentDuanLuo\">&nbsp;&nbsp;&nbsp;&nbsp;三、科研单位转让技术，应持各级科委技术市场管理机构出具的技术合同认定登记证明，向主管税务机关提出申请。由主管税务机关审核批准后，方可享受免征营业税照顾。</div>  	<div class=\"TextContentDuanLuo\"> &nbsp;&nbsp;&nbsp;&nbsp;四、本规定从1994年1月1日起执行。本文下发之前已征收的营业税退还给科研单位。</div> </div>"));

		layout.addView(textView);
		scrollView.addView(layout);

		setContentView(scrollView);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case 1:
			textView.setTextSize(18f);
			break;
		case 2:
			textView.setTextSize(16f);
			break;
		case 3:
			textView.setTextSize(14f);
			break;
		}
	}
}

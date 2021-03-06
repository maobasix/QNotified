/* QNotified - An Xposed module for QQ/TIM
 * Copyright (C) 2019-2020 xenonhydride@gmail.com
 * https://github.com/cinit/QNotified
 *
 * This software is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software.  If not, see
 * <https://www.gnu.org/licenses/>.
 */
package nil.nadph.qnotified.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.mobileqq.widget.BounceScrollView;
import nil.nadph.qnotified.hook.CardMsgHook;
import nil.nadph.qnotified.script.QNScriptManager;
import nil.nadph.qnotified.ui.ResUtils;
import nil.nadph.qnotified.util.LicenseStatus;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static nil.nadph.qnotified.ui.ViewBuilder.*;
import static nil.nadph.qnotified.util.SendBatchMsg.clickToBatchMsg;
import static nil.nadph.qnotified.util.Utils.dip2px;

@SuppressLint("Registered")
public class AlphaTestFuncActivity extends IphoneTitleBarActivityCompat {

    TextView __js_status;

    @Override
    public boolean doOnCreate(Bundle bundle) {
        super.doOnCreate(bundle);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ViewGroup.LayoutParams mmlp = new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        LinearLayout __ll = new LinearLayout(this);
        __ll.setOrientation(LinearLayout.VERTICAL);
        ViewGroup bounceScrollView = new BounceScrollView(this, null);
        bounceScrollView.setLayoutParams(mmlp);
        bounceScrollView.addView(ll, new ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        LinearLayout.LayoutParams fixlp = new LinearLayout.LayoutParams(MATCH_PARENT, dip2px(this, 48));
        RelativeLayout.LayoutParams __lp_l = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        int mar = (int) (dip2px(this, 12) + 0.5f);
        __lp_l.setMargins(mar, 0, mar, 0);
        __lp_l.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        __lp_l.addRule(RelativeLayout.CENTER_VERTICAL);
        RelativeLayout.LayoutParams __lp_r = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        __lp_r.setMargins(mar, 0, mar, 0);
        __lp_r.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        __lp_r.addRule(RelativeLayout.CENTER_VERTICAL);
        if (!LicenseStatus.isAsserted()) {
            TextView tv = new TextView(this);
            tv.setText("你是怎么进来的???????????????????");
            tv.setTextColor(ResUtils.skin_red);
            tv.setTextSize(30);
            ll.addView(tv, MATCH_PARENT, WRAP_CONTENT);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        AlphaTestFuncActivity.this.finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            ll.addView(subtitle(this, "Alpha内测功能 请勿截图此页面"));
            ll.addView(subtitle(this, "遗留功能"));//群发已不再维护
            ll.addView(newListItemButton(this, "群发文本消息", "年少不知号贵-理性使用以免永冻", null, clickToBatchMsg()));
            ll.addView(newListItemHookSwitchInit(this, "发送卡片消息", "ArkAppMsg(json)+StructMsg(xml)", CardMsgHook.get()));
            ll.addView(subtitle(this, "卡片消息使用说明:先输入卡片代码(聊天界面),后长按发送按钮\n勿滥用此功能! 频繁使用此功能被举报可能封号"));
            ll.addView(subtitle(this, "警告: 请勿发送违规内容! 在您使用 群发文本消息 及 发送卡片消息 时, " +
                    "本模块会向服务器报告您发送的消息内容以及当前QQ号, 如您不同意, 请勿使用群发与卡片消息功能!", Color.RED));
            ViewGroup _t;
            ll.addView(_t = newListItemButton(this, "管理脚本(.java)", "请注意安全, 合理使用", "N/A", clickToProxyActAction(ManageScriptsActivity.class)));
            __js_status = _t.findViewById(R_ID_VALUE);
        }
        __ll.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        this.setContentView(bounceScrollView);
        LinearLayout.LayoutParams _lp_fat = new LinearLayout.LayoutParams(MATCH_PARENT, 0);
        _lp_fat.weight = 1;

        setContentBackgroundDrawable(ResUtils.skin_background);
        setTitle("Alpha内测功能");
        return true;
    }

    @Override
    public void doOnResume() {
        super.doOnResume();
        if (__js_status != null) {
            __js_status.setText(QNScriptManager.getEnableCount() + "/" + QNScriptManager.getAllCount());
        }
    }
}

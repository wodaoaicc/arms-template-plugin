package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.commonAnnotation

fun armsActivity(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) =
    if (isKt) armsActivityKt(provider) else armsActivityJava(provider)

private fun armsActivityKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.activityPackageName.value}
import android.app.Activity
import android.os.Bundle
import com.jess.arms.di.component.AppComponent
import com.tphy.base.activity.SimpleBaseActivity
import ${provider.appPackageName.value}.databinding.Activity${provider.pageName.value}Binding
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter
import ${provider.appPackageName.value}.R

${commonAnnotation(provider)}
class ${provider.pageName.value}Activity : SimpleBaseActivity<${provider.pageName.value}Presenter,Activity${provider.pageName.value}Binding>() , ${provider.pageName.value}Contract.View {
    override fun setupActivityComponent(appComponent: AppComponent) {
        Dagger${provider.pageName.value}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .${provider.pageName.value[0].toLowerCase()}${
    provider.pageName.value.substring(
        1,
        provider.pageName.value.length
    )
}Module(${provider.pageName.value}Module(this))
                .build()
                .inject(this)
    }
    
    override fun initData(savedInstanceState: Bundle?) {
        initListener()
    }
    private fun initListener() {
    
    }
    
    override fun getActivity(): Activity = this
    
    override fun viewBing(): Activity${provider.pageName.value}Binding {
        return binding
    }
}
    
"""

private fun armsActivityJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.activityPackageName.value};
import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Bundle;
import com.jess.arms.di.component.AppComponent;
import com.tphy.base.activity.SimpleBaseActivity;
import ${provider.appPackageName.value}.databinding.Activity${provider.pageName.value}Binding;
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component;
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module;
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract;
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter;
import ${provider.appPackageName.value}.R;

${commonAnnotation(provider)}
public class ${provider.pageName.value}Activity extends SimpleBaseActivity<${provider.pageName.value}Presenter,Activity${provider.pageName.value}Binding> implements ${provider.pageName.value}Contract.View {
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        Dagger${provider.pageName.value}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
//                .view(this)
                .build()
                .inject(this);
    }
    
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initListener();
    }
    
    public void initListener() {
    
    }
    
    @Override
    public Activity getActivity(){
        return this;
    }
    
    @Override
    public Activity${provider.pageName.value}Binding viewBing() {
        return getBinding();
    }
}
    
"""
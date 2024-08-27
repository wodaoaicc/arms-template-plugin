package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.commonAnnotation

fun armsFragment(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) = if (isKt) armsFragmentKt(provider) else armsFragmentJava(provider)

private fun armsFragmentKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.fragmentPackageName.value}
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tphy.base.activity.SQBaseFragment
import ${provider.componentPackageName.value}.databinding.Fragment${provider.pageName.value}Binding
import com.jess.arms.di.component.AppComponent
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter
import ${provider.appPackageName.value}.R

${commonAnnotation(provider)}
class ${provider.pageName.value}Fragment : SQBaseFragment<${provider.pageName.value}Presenter>() , ${provider.pageName.value}Contract.View{
    override fun setupFragmentComponent(appComponent:AppComponent) {
        Dagger${provider.pageName.value}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .${provider.pageName.value[0].toLowerCase()}${provider.pageName.value.substring(1,provider.pageName.value.length)}Module(${provider.pageName.value}Module(this))
                .build()
                .inject(this)
    }
   
    /**
     * 在 onActivityCreate()时调用
     */
    override fun initData(savedInstanceState: Bundle?) {
        initListener()
    }
    
    private fun initListener() {
    
    }
    
}
    
"""


fun armsFragmentJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.fragmentPackageName.value};
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tphy.base.activity.SQBaseFragment;
import ${provider.componentPackageName.value}.databinding.Fragment${provider.pageName.value}Binding;
import com.jess.arms.di.component.AppComponent;
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component;
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module;
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract;
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter;
import ${provider.appPackageName.value}.R;

${commonAnnotation(provider)}
class ${provider.pageName.value}Fragment extends SQBaseFragment<${provider.pageName.value}Presenter,${provider.pageName.value}> implements ${provider.pageName.value}Contract.View{
    
    public static ${provider.pageName.value}Fragment newInstance() {
        ${provider.pageName.value}Fragment fragment = new ${provider.pageName.value}Fragment();
        return fragment;
    }
    
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        Dagger${provider.pageName.value}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .${provider.pageName.value[0].toLowerCase()}${provider.pageName.value.substring(1,provider.pageName.value.length)}Module(${provider.pageName.value}Module(this))
//                .view(this)
                .build()
                .inject(this);
    }
    
    /**
     * 在 onActivityCreate()时调用
     */
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initListener();
    }
    
    private void initListener() {
   
	}
    
}
    
"""
package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.armsAnnotation

fun armsModel(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) = if (isKt) armsModelKt(provider) else armsModelJava(provider)

private fun armsModelKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.modelPackageName.value}
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
${
    if (provider.needActivity.value && provider.needFragment.value)
        "import com.jess.arms.di.scope.ActivityScope"
    else if (provider.needActivity.value)
        "import com.jess.arms.di.scope.ActivityScope"
    else if (provider.needFragment.value)
        "import com.jess.arms.di.scope.FragmentScope"
    else ""
}
import javax.inject.Inject
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract

$armsAnnotation
${
    if (provider.needActivity.value && provider.needFragment.value)
        "@ActivityScope"
    else if (provider.needActivity.value)
        "@ActivityScope"
    else if (provider.needFragment.value)
        "@FragmentScope"
    else ""
}
class ${provider.pageName.value}Model
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), ${provider.pageName.value}Contract.Model{

    override fun onDestroy() {
          super.onDestroy()
    }
}   
"""


fun armsModelJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.modelPackageName.value};
import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
${
    if (provider.needActivity.value && provider.needFragment.value)
        "import com.jess.arms.di.scope.ActivityScope;"
    else if (provider.needActivity.value)
        "import com.jess.arms.di.scope.ActivityScope;"
    else if (provider.needFragment.value)
        "import com.jess.arms.di.scope.FragmentScope;"
    else ""
}
import javax.inject.Inject;
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract;

$armsAnnotation
${
    if (provider.needActivity.value && provider.needFragment.value)
        "@ActivityScope"
    else if (provider.needActivity.value)
        "@ActivityScope"
    else if (provider.needFragment.value)
        "@FragmentScope"
    else ""
}
public class ${provider.pageName.value}Model extends BaseModel implements ${provider.pageName.value}Contract.Model{
   
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}   
"""
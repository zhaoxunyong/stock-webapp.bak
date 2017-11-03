//session丢失后跳转到会话丢失页面
$.bindge("sessionLost",function(event){
//    GlobalDialogUtils.openModalDialog("autoRedirectToLoginPage","登录超时",_contextPath + "/mainframe/toSessionLostError.action",400,300);
//    return true;
    
    GlobalDialogUtils.openModalDialog("sessionLostError","登录超时","/sessionLostError",400,300);
    return true;
});
//系统自动跳转到登录页面
$.bindge("returnLoginPage",function(event){
    //window.location.href = _contextPath + "/mainframe/toLogin.action";
    //top.location.href = "http://localhost:8081/cas/login?service=http://localhost:8080";
    //top.location.href = casLoginUrl;
	// 跳到主頁，會自動導向cas登錄頁
    top.location.href = "/";
});
var euiIndexTabs = null;
var euiIndexTabsMenu = null;
$(function(){
	/**
     * 初始化页面tabs 
     */
	euiIndexTabs = $('#tabs').tabs({
		fit : true,
        border : false,
        onContextMenu : function(e, title) {
        	e.preventDefault();
			$('#index_tabsMenu').menu('show', {
				left: e.pageX,
				top: e.pageY
			}).data('tabTitle', title);            	  
        },
        tools : [{
            iconCls : 'icon-transmit',
            handler : function() {
                var href = euiIndexTabs.tabs('getSelected').panel('options').href;
                if (href) {
                    /*说明tab是以href方式引入的目标页面*/
                    var index = euiIndexTabs.tabs('getTabIndex', euiIndexTabs.tabs('getSelected'));
                    euiIndexTabs.tabs('getTab', index).panel('refresh');
                } else {
                    /*说明tab是以content方式引入的目标页面*/
                	refresh();
                }
            }
        }, {
            iconCls : 'icon-delete',
            handler : function() {
                var index = euiIndexTabs.tabs('getTabIndex', euiIndexTabs.tabs('getSelected'));
                var tab = euiIndexTabs.tabs('getTab', index);
                if (tab.panel('options').closable) {
                    euiIndexTabs.tabs('close', index);
                } else {
                    $.messager.alert('提示', '[' + tab.panel('options').title + ']不可以被關閉！', 'error');
                }
            }
        }],
        onSelect : function(title,index){
			$('#tabs').tabs('getTab', index).panel();
		}
	});
	/**
     * 设置euiIndexTabsMenu工具菜單
     */
	euiIndexTabsMenu = $('#index_tabsMenu').menu({
		onClick : function(item) {
            var curTabTitle = $(this).data('tabTitle');
            var type = $(item.target).attr('title');

            if (type === 'refresh') {
            	refresh();
            }else if (type === 'close') {
                var t = euiIndexTabs.tabs('getTab', curTabTitle);
                if (t.panel('options').closable) {
                    euiIndexTabs.tabs('close', curTabTitle);
                }
                return;
            }else{
                var allTabs = euiIndexTabs.tabs('tabs');
                var closeTabsTitle = [];
                $.each(allTabs, function() {
                    var opt = $(this).panel('options');
                    if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
                        closeTabsTitle.push(opt.title);
                    } else if (opt.closable && type === 'closeAll') {
                        closeTabsTitle.push(opt.title);
                    }
                });
                for (var i = 0; i < closeTabsTitle.length; i++) {
                    euiIndexTabs.tabs('close', closeTabsTitle[i]);
                }
            }
        }
	});
});

/*
 * 刷新
 */
function refresh(){
	 var panel = euiIndexTabs.tabs('getSelected').panel('panel');
     var frame = panel.find('iframe');
     try {
         if (frame.length > 0) {
             for (var i = 0; i < frame.length; i++) {
                 frame[i].contentWindow.document.write('');
                 frame[i].contentWindow.close();
                 frame[i].src = frame[i].src;
             }
             if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
                 try {
                     CollectGarbage();
                 } catch (e) {
                 }
             }
         }else{
         	 window.location.reload()
         }
     } catch (e) {
     }
}

//监听菜单点击时间，并打开tab
$.bindge('menu_click',function(event,parms){
	openTab(parms.title,parms.href)
})
/**
 * 在iFrame中打开一个新tab
 * @param title
 * @param href
 */
function openTab(title,href){
    var e = $('#tabs').tabs('exists',title);
    if(e){
    	var currTab = $('#tabs').tabs('getTab', title);
    	var index = $('#tabs').tabs('getTabIndex',currTab);
        $('#tabs').tabs('getTab', index).panel('open');
    }else{
        $('#tabs').tabs('add',{
            title:title,
            content:'<iframe name="indextab" scrolling="none" src="'+href+'" frameborder="0" style="width:100%;height:100%;"></iframe>',
            iconCls:'',
            closable:true
        });
    }
}
/**
 * 在iFrame中打开一个新tab
 */
$.bindge('addOrSelectTab',function(event,parms){
	openTab(parms.title,parms.href)
})
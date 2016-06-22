var operater_module = 'operater';

// 从easyui 官网文档中拷贝
// http://www.jeasyui.com/demo/main/index.php?plugin=DataGrid&theme=default&dir=ltr&pitem=#
$(function() {
	var pager = $(getDataGridIdWithPound(operater_module)).datagrid().datagrid(
			'getPager');
	pager.pagination({
		pageList : [ 10, 20, 30 ],
		displayMsg : '共 {total} 条记录，从 {from} 到 {to} ',
		buttons : [ {
			iconCls : 'icon-search',
			text : '查询',
			handler : function() {
				initOperaterSearchWindow();
			}
		}, {
			iconCls : 'icon-add',
			text : '新增',
			handler : function() {
				initOperaterAddWindow();
			}
		}, {
			iconCls : 'icon-remove',
			text : '删除',
			handler : function() {
				deleteRows(operater_module);
			}
		}, {
			iconCls : 'icon-undo',
			text : '取消查询',
			handler : function() {
				list(operater_module);
			}
		} ]
	});
	$(getWinIdWithPound(operater_module)).window({
		onBeforeClose : function() {
			resetAllAlt();
		}

	});
});

function initOperaterAddWindow() {
	openWindow(operater_module, '新增', 455, 327);
	var win = getWinIdWithPound(operater_module);
	$(win + '_submit').unbind('click').click(function(event) {
		saveRow(operater_module);
	});
	setRequiredOperaterPassword(true);

	setRequiredOperaterCommons(true);
	showPassword();
}

function initOperaterSearchWindow() {
	openWindow(operater_module, '查询', 455, 285);
	var win = getWinIdWithPound(operater_module);
	$(win + '_submit').unbind('click').click(function(event) {
		searchRows(operater_module);
	});
	setRequiredOperaterCommons(false);
	hidePassword();
}

function dblClickOperaterRow(rowIndex, rowData) {
	initOperaterEditWindow(rowData);
}

function initOperaterEditWindow(rowData) {
	openWindow(operater_module, '修改', 423, 310);
	showPassword();
	var win = getWinIdWithPound(operater_module);
	$(win).form('load', rowData);
	$(win + '_submit').unbind('click').click(function(event) {
		updateRow(operater_module, rowData['id']);
	});
	setRequiredOperaterPassword(false);

	setRequiredOperaterCommons(true);
}

function showPassword() {
	var win = getWinIdWithPound(operater_module);
	$(win + '_password_tr').show();
}

function hidePassword() {
	var win = getWinIdWithPound(operater_module);
	$(win + '_password_tr').hide();
}

/**
 * 将所有的提示信息去除(easyUI在关闭窗口时，会有一些提示信息没有隐藏，比如：“不能为空”)
 */
function resetAllAlt() {
	var win = getWinIdWithPound(operater_module);
	$(win + '_password').validatebox({
		required : false
	});
	$(win + '_name').validatebox({
		required : false
	});
	$(win + '_password').validatebox('isValid');
	$(win + '_name').validatebox('isValid');
}

function setRequiredOperaterPassword(isRequired) {
	// 用prop/attr都没有效果
	var win = getWinIdWithPound(operater_module);
	$(win + '_password').validatebox({
		required : isRequired
	});
}

function setRequiredOperaterCommons(isRequired) {
	// 用prop/attr都没有效果
	var win = getWinIdWithPound(operater_module);
	$(win + '_name').validatebox({
		required : isRequired
	});
	if (!isRequired) {// combox bug
		$(win + '_name').validatebox('isValid');
	}
}

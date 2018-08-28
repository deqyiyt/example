$(function(){
	//实例化编辑器
	var um = UM.getEditor('content',{
		UMEDITOR_HOME_URL: ias.config.umeditorHomeUrl
		,toolbar: [
	        'source | undo redo | bold italic underline strikethrough | emotion image video | superscript subscript formula | forecolor backcolor | removeformat |',
	        'insertorderedlist insertunorderedlist | selectall cleardoc | paragraph fontsize',
	        '| justifyleft justifycenter justifyright justifyjustify |',
	        'link unlink ',
	        '| horizontal print preview fullscreen'
	    ]
		,imageUrl: ias.config.uploadImgUrl //图片上传提交地址
		,autoClearEmptyNode:true
	    ,autoHeightEnabled:false
	    ,initialFrameWidth:"100%"
	    ,initialFrameHeight:300
	    ,enterTag:""
	    ,focus:true
	    ,zIndex: 10052
	});
	um.addListener('afterfullscreenchange',function(){
		console.log(um.$container);
	});
});
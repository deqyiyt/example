/**
 * @description
 * 1.拖放文件到编辑区域，自动上传并插入到选区
 * 2.插入粘贴板的图片，自动上传并插入到选区
 * @author Jinqn
 * @date 2013-10-14
 */
UM.plugins['autoupload'] = function () {

    var me = this;

    me.setOpt('pasteImageEnabled', true);
    me.setOpt('dropFileEnabled', true);
    var sendAndInsertImage = function (file, editor) {
        //模拟数据
        var fd = new FormData();
        fd.append(editor.options.imageFieldName || 'upfile', file, file.name || ('blob.' + file.type.substr('image/'.length)));
        fd.append('type', 'ajax');
        $.ajax({
			url:me.options.imageUrl
			, data:fd
			, processData: false
			, contentType: false
			, type : "POST"
			, context : editor.$body
			, success: function(link){
				if(me.options.pasteImageInsert != false) {
					try {
						if(!link.isValidURL()) {
							link = me.options.imagePath + link;
						}
		                editor.execCommand('insertimage', {
		                    src: link,
		                    _src: link
		                });
		            } catch (er) {
		            }
				}
			}
		});
    };

    function getPasteImage(e) {
        return e.clipboardData && e.clipboardData.items && e.clipboardData.items.length == 1 && /^image\//.test(e.clipboardData.items[0].type) ? e.clipboardData.items : null;
    }

    function getDropImage(e) {
        return  e.dataTransfer && e.dataTransfer.files ? e.dataTransfer.files : null;
    }

    me.addListener('ready', function () {
        if (window.FormData && window.FileReader) {
            var autoUploadHandler = function (e) {
                var items;
                //获取粘贴板文件列表或者拖放文件列表
                items = e.type == 'paste' ? getPasteImage(e.originalEvent) : getDropImage(e.originalEvent);
                if (items) {
                    var len = items.length,
                        file;
                    while (len--) {
                        file = items[len];
                        if (file.getAsFile) file = file.getAsFile();
                        if (file && file.size > 0 && /image\/\w+/i.test(file.type)) {
                            sendAndInsertImage(file, me);
                        }
                    }
                }

            };
            me.getOpt('pasteImageEnabled') && me.$body.on('paste', autoUploadHandler);
            me.getOpt('dropFileEnabled') && me.$body.on('drop', autoUploadHandler);

            //取消拖放图片时出现的文字光标位置提示
            me.$body.on('dragover', function (e) {
                if (e.originalEvent.dataTransfer.types[0] == 'Files') {
                    e.preventDefault();
                }
            });
        }
    });

    // 转存 base64 图片
    me.addListener('transferBase64Image', function () {
        utils.each(me.document.getElementsByTagName('img'), function (img, i){
            var options = {}, base64, id;
            if (base64 = getBase64ImageData(img)) {
                id = img.id = 'base64img_' + (+new Date());
                options['base64'] = true;
                options[me.getOpt('imageFieldName')] = base64;
                $.post(me.getOpt('imageUrl'), options, function(r){
                    var json = eval('('+r+')'),
                        $img = $('#' + id),
                        link;
                    if (json.state == 'SUCCESS' && json.url) {
						var link = json.url;
						if(!link.isValidURL()) {
							link = me.getOpt('imagePath') + json.url;
						}
                        $img.attr('src', link);
                        $img.attr('_src', link);
                    } else if(json["status"] == 200) {
						var link = json.results.url;
						if(!link.isValidURL()) {
							link = me.getOpt('imagePath') + json.url;
						}
                        $img.attr('src', link);
                        $img.attr('_src', link);
					}
                });
            }
        });
    });
    /*
     * 这个不知道用意是干嘛的，每次上传后居然扫描页面中的 所有 img的src是base64位的，然后把图片给上传到后台
     *
    me.addListener('ready', function () {
         function transferHandler(){
            setTimeout(function (){
                me.fireEvent('transferBase64Image');
            });
        }
    	me.$body.on('paste drop', transferHandler);
    });
    */

    function getBase64ImageData(img){
        var src = img.src, arr;
        if (src.length > 60 && (arr = src.match(/^(data:[^;]*;base64,)/))) {
            return src.substring(arr[1].length);
        }
    }

};
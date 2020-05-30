$(function () {
  $('td').on('click', '.buybtn', function () {
    console.log('click invoke..')
    return confirm('确认购买该商品吗')
  })
  $('td').on('click', '.delbtn', function () {
    console.log('click invoke..')
    return confirm('确认删除该商品吗')
  })
  var uids = {uids: []}
  $('.cuid').click(function () {
    uids.uids = []
    $('.cuid:checked').each(function () {
      uids.uids.push($(this).val())
    })
  })
  $('.buybtns').click(function () {
    if (uids.uids.length <= 0) return
    if (confirm('确认批量购买吗？')) {
      var json = JSON.stringify(uids)
      $('#deleteuids').val(json)
      $('#form1').attr('action', '/buys')
      form1.submit()
      // location.href="/buys/"+json;
    }
  })
})

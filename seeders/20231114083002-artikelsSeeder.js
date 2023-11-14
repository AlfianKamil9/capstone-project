'use strict';

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface) {
    await queryInterface.bulkInsert(
      'Artikels',
      [
        {
          title: 'Title 1',
          image: 'http://link.com',
          subTitle: 'Subtitle Title 1',
          content: `Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Diam ut venenatis tellus in metus vulputate eu scelerisque felis. Ut diam quam nulla porttitor massa id neque aliquam. Purus faucibus ornare suspendisse sed nisi lacus sed. Purus sit amet volutpat consequat mauris nunc congue nisi. Mattis molestie a iaculis at erat pellentesque. Sodales neque sodales ut etiam sit amet nisl purus. Congue nisi vitae suscipit tellus mauris. Eget velit aliquet sagittis id consectetur purus ut. Eu feugiat pretium nibh ipsum consequat nisl vel. Viverra aliquet eget sit amet tellus cras. Sem fringilla ut morbi tincidunt augue. Dapibus ultrices in iaculis nunc sed augue lacus viverra. Nulla at volutpat diam ut venenatis tellus in. Vestibulum sed arcu non odio. Et molestie ac feugiat sed lectus.

At in tellus integer feugiat scelerisque varius morbi enim nunc. Nisi est sit amet facilisis magna etiam. Viverra accumsan in nisl nisi scelerisque eu ultrices vitae. Nunc consequat interdum varius sit amet mattis vulputate enim. Neque viverra justo nec ultrices. Augue ut lectus arcu bibendum at varius vel. Volutpat lacus laoreet non curabitur gravida arcu. Donec pretium vulputate sapien nec sagittis. Venenatis a condimentum vitae sapien pellentesque habitant. In fermentum et sollicitudin ac orci phasellus egestas tellus rutrum. Hac habitasse platea dictumst vestibulum rhoncus est pellentesque elit. Gravida cum sociis natoque penatibus et magnis dis parturient montes. Mi ipsum faucibus vitae aliquet nec ullamcorper sit. Placerat vestibulum lectus mauris ultrices eros. Eu nisl nunc mi ipsum.

Amet porttitor eget dolor morbi non arcu risus quis varius. Proin fermentum leo vel orci porta non pulvinar neque. Id semper risus in hendrerit gravida rutrum quisque non tellus. Cursus mattis molestie a iaculis at erat pellentesque adipiscing commodo. Quis ipsum suspendisse ultrices gravida dictum fusce ut placerat. Nullam ac tortor vitae purus. Nibh tellus molestie nunc non blandit massa. Venenatis tellus in metus vulputate. Lorem donec massa sapien faucibus. Vel pharetra vel turpis nunc eget lorem dolor. Euismod elementum nisi quis eleifend quam adipiscing. Quam viverra orci sagittis eu volutpat. Mattis pellentesque id nibh tortor id aliquet lectus proin nibh.

Lectus nulla at volutpat diam ut venenatis. Ut enim blandit volutpat maecenas volutpat blandit. Faucibus turpis in eu mi bibendum neque. Lacus laoreet non curabitur gravida arcu ac tortor dignissim. Lorem ipsum dolor sit amet consectetur. Tincidunt vitae semper quis lectus nulla. Amet luctus venenatis lectus magna fringilla urna porttitor rhoncus dolor. Nisl nisi scelerisque eu ultrices. Hendrerit dolor magna eget est lorem ipsum dolor sit amet. Euismod lacinia at quis risus sed vulputate odio ut enim. Eget est lorem ipsum dolor. Risus feugiat in ante metus dictum at tempor commodo ullamcorper.

Amet risus nullam eget felis eget nunc. Ullamcorper velit sed ullamcorper morbi tincidunt ornare. Aliquam purus sit amet luctus venenatis lectus. Amet mauris commodo quis imperdiet. Rhoncus urna neque viverra justo nec ultrices. Tincidunt ornare massa eget egestas purus. A cras semper auctor neque vitae tempus quam pellentesque. A condimentum vitae sapien pellentesque habitant morbi tristique senectus. Et odio pellentesque diam volutpat. Viverra orci sagittis eu volutpat odio. Vel elit scelerisque mauris pellentesque pulvinar pellentesque habitant morbi tristique. Velit euismod in pellentesque massa placerat duis ultricies. Adipiscing vitae proin sagittis nisl rhoncus mattis rhoncus urna.`,
          createdAt: new Date(),
          updatedAt: new Date(),
        },
      ],
      {}
    );
  },

  async down(queryInterface) {
    await queryInterface.bulkDelete('Artikels', null, {});
  },
};

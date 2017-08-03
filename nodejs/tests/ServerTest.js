var request = require('supertest');
var mockgoose = require('mockgoose');
var mongoose = require('mongoose');

mockgoose(mongoose);

describe('loading express', function() {
  var server;
  beforeEach(function() {
    mockgoose.reset();

    server = require('../Server');
  });
  afterEach(function() {
    server.close();
  });
  it('responds to /', function testSlash(done) {
    request(server)
      .get('/')
      .expect(200, done);
  });
  it('404 everything else', function testPath(done) {
    request(server)
      .get('/foo/bar')
      .expect(404, done);
  });
});
